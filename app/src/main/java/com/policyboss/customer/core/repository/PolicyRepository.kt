package com.policyboss.customer.core.repository

import com.policyboss.customer.feature.tabfeatures.policyVault.model.policyVaultModel.PolicyVaultPolicy


/*
ViewModels never share data with each other. Repositories share data.

Think of it like this
                 API
                  │
                  ▼
         PolicyRepository
                  │
        cachedPolicies
          (Memory Cache)
          /            \
         ▼              ▼
 HomeViewModel   PolicyVaultViewModel

Both ViewModels ask the same repository
 */
class PolicyRepository {

    private var cachedPolicies: List<PolicyVaultPolicy>? = null

    suspend fun getPolicies(): List<PolicyVaultPolicy>? {

        cachedPolicies?.let {
            return it
        }

        //val response = api.getPolicies()
        val response = null
        cachedPolicies = response

        return response
    }
}


/*
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
In your insurance app

I'd eventually build something like this:

                 Policy API
                      │
                      ▼
              PolicyRepository
              ┌──────────────┐
              │ Memory Cache │
              └──────────────┘
                      │
              ┌──────────────┐
              │ Room Database│
              └──────────────┘
                  ▲        ▲
                  │        │
                  │        │
          HomeViewModel  PolicyVaultViewModel
HomeViewModel requests a small preview (e.g., the first few policies).
PolicyVaultViewModel requests the full list.

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */



/*

Flow
User opens Home

↓

HomeViewModel

↓

repository.getPolicies()

↓

No cache

↓

API

↓

Cache filled

Later:

User opens Policy Vault

↓

PolicyVaultViewModel

↓

repository.getPolicies()

↓

Returns cached list

No second API call.

Your concern: "Will each ViewModel pull data?"

Yes, but that's okay.

HomeViewModel
      │
      ▼
Repository

PolicyVaultViewModel
      │
      ▼
Repository

ClaimViewModel
      │
      ▼
Repository

They all ask the repository.

The repository is responsible for avoiding duplicate work.

Think of it like this

The repository is a librarian.

Every ViewModel asks:

"Give me the policies."

The librarian checks:

📚 "Do I already have them?" → Return cached data.
🌐 "I don't have them." → Fetch from API, store them, then return.

The ViewModels don't care where the data came from.

In your insurance app

I would likely have separate repositories:

HomeRepository
PolicyRepository
ClaimRepository
PrivilegeRepository
UserRepository
NotificationRepository

Then:

HomeViewModel

might depend on:

HomeRepository
PolicyRepository
NotificationRepository

while

PolicyVaultViewModel

depends only on:

PolicyRepository

This keeps each feature focused on the data it actually needs.

My recommendation for your project
                 API
                  │
        ---------------------
        │         │         │
        ▼         ▼         ▼
 PolicyRepo  ClaimRepo  UserRepo
        │
        │ (cache)
        │
  ┌─────┴──────────────┐
  ▼                    ▼
HomeViewModel   PolicyVaultViewModel
 */