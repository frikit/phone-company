package com.phone.models

case class CustomerCall(
                         val customerID: String,
                         val phoneNumber: PhoneNumber,
                         val duration: CallDuration
                       )
