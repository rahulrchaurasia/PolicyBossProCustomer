# PolicyBossProCustomer

==== User categories  ==>

Scenario 1
--------------------------------------------------
User came from Microsite

Goal:
Earn on renewals

Focus:
NPOS / Privileged User

Priority:
NPOS banners
Renewal reminders
Policy expiry nudges



Scenario 2
--------------------------------------------------
User bought policy and downloaded app

Goal:
Access recently purchased policy

Priority:
Latest policy first
Download policy
Email sync
Contact sync



Scenario 3
--------------------------------------------------
New PlayStore user

Goal:
Explore insurance products

Priority:
Curated products
Discovery
Small NPOS nudges
Empty Policy Vault
Personalized journey


                    HomeScreen

                         |
         ----------------------------------
         |                                |
New User                         NPOS User
(Explorer)                       (Policy Owner)

Discover products                Manage policies

Buy insurance                    Renew insurance

Learn platform                   View policy details

Become customer                  Become privileged user

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Recommended architecture
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
HomeScreen

                               |

                        HomeViewModel

                               |

                       HomeExperience

                               |

    ---------------------------------------------------------------

|                  |                    |

Explore          AccessPolicy         EarnRenewals

Scenario 3       Scenario 2            Scenario 1


>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Create this 'enum'
'enum' class HomeExperience {

    EXPLORE,

    ACCESS_POLICY,

    EARN_RENEWALS
}

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Architecture graph
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

HomeScreen

                      |

                 HomeViewModel

                      |

                HomeUiState

                      |

              HomeExperience

                      |

------------------------------------------------------

|                   |                    |

EXPLORE         ACCESS_POLICY      EARN_RENEWALS

Scenario 3       Scenario 2         Scenario 1



>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

Scenario 2 ordering : ---- >
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
..........................................................

            User intent:
            
            I bought policy
            
            ↓
            
            I need policy now
            
            Order:
            
            Welcome
            
            ↓
            
            Latest Policy Card
            
            ↓
            
            Download Policy CTA
            
            ↓
            
            Sync Email Nudge
            
            ↓
            
            Become Privileged User
            
            ↓
            
            Curated Policies
            
            ↓
            
            Support
            
            ↓

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Articles
Scenario 3 ordering
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
..........................................................

        User intent:
        
        I am exploring
        
        Order:
        
        Welcome
        
        ↓
        
        Banner
        
        ↓
        
        Curated Policies
        
        ↓
        
        Personalized Journey
        
        ↓
        
        Earning Opportunity
        
        ↓
        
        Support
        
        ↓
        
        Empty Policy Vault
        
        ↓
        
        Articles
        Scenario 1 ordering (future)
        
        User intent:
        
        I want to earn
        
        Order:
        
        Welcome
        ↓
        NPOS Banner
        ↓
        Privilege User
        ↓
        Renewal Reminder
        ↓
        
        Policy Expiry
        
        ↓
        
        Policy Vault
        
        ↓
        
        Support



>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
> 
> First, let's identify common vs different

Common (both Scenario 2 & 3)
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

These already exist in your HomeScreen.

HeroSection

QuickActionSection

EarningOpportunitySection

CuratedPoliciesSection

BosspediaSection

FooterTrustSection

These should remain reusable.

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Scenario 2 only
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Latest policy first

Download policy CTA

Email sync CTA

Contact sync CTA

Policy Vault at top

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Scenario 3 only
>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
Empty Policy Vault

Small NPOS nudges

Discovery first

Personalized journey



>>>>>>>>>>>>>>
> 
> 
> Your implementation order should be this
Sprint 1

Prepare architecture.

HomeExperience

ACCESS_POLICY

EXPLORE

Done.

Sprint 2

Implement Scenario 2.

Order:

Hero

↓

QuickActions

↓

PolicyVault

↓

Download CTA

↓

Sync Mail CTA

↓

Sync Contacts CTA

↓

Earning

↓

Curated Policies

↓

Bosspedia

↓

Footer

Only Scenario 2.

Do not touch Scenario 3 yet.

Sprint 3

Implement Scenario 3.

Order:

Hero

↓

QuickActions

↓

Earning

↓

Curated Policies

↓

Empty Policy Vault

↓

Bosspedia

↓

Footer

No Download CTA.

No Sync CTA.

Very important

Do NOT create these right now:

AccessPolicyScreen.kt

ExploreScreen.kt