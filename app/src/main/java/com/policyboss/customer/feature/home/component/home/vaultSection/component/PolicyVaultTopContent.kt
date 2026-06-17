package com.policyboss.customer.feature.home.component.home.vaultSection.component

@Composable
private fun TopContent(

    policy: VaultPolicy,

    onViewDetailsClick: () -> Unit
) {

    Box {

        PolicyHeaderImage(
            policy.carImage
        )

        ExpiryBadge(

            text = policy.daysLeft,

            modifier = Modifier

                .align(
                    Alignment.TopEnd
                )
        )

        Column(

            modifier = Modifier

                .align(
                    Alignment.CenterEnd
                )
        ) {

            Text(

                text = policy.vehicleName,

                color = Color.White,

                fontWeight = FontWeight.Bold
            )

            Text(

                text = policy.vehicleNumber,

                color = Color.White
            )

            Row(

                modifier = Modifier.clickable {

                    onViewDetailsClick()
                },

                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(

                    text = "View details",

                    color = Color.White
                )

                Icon(

                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,

                    contentDescription = null,

                    tint = Color.White
                )
            }
        }
    }
}