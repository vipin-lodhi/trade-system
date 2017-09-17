The said application is built using spring-boot

PART - 1
--------------------------------------------------------------------------------------------------------------------------------------------------
Steps to run the project :
===================================================================================================================================================
1. Go to the Main.java right Click on it and choose Run 'Main'(if you are using IJ editor).
2. The api would be available on the http://localhost:8080/api/tradeValidation.
3. You can use postman to test the API/Or You can use the TradeValidationClient.java file.
4. Sample test trade data given in the problem statement are already available in the TradeValidationClient.java run this client
   program and we can see the output on the console logs
===================================================================================================================================================

PART - 2
---------------------------------------------------------------------------------------------------------------------------------------------------------
Few Comments on below point: (please see my comments after -->)
==========================================================================================================================================================
   Technical requirements:
        1. The service shall expose a REST interface consuming trades in JSON format and returning validation result to the client
        2. Service shall be flexible to extend the validation logic in terms of:
            - new business validation rules --> we can add any validation Rule by adding new class in the rule package and registering in
                                               TradeValidationService.
            - new products to be supported --> we can add new product in ProductTypeEnum file and if new rule is associated with that product type
                                               then follow above steps.
        3. Service should support graceful shutdown -- It is possible using the Spring Boot Actuator due to time constraint not able to build it.

   Business requirements:
    1. The following basic validation rules shall be implemented:
    ALL:
        - value date cannot be before trade date --> Handled
        - value date cannot fall on weekend or non-working day for currency
          (candidate can use any public services e.g. http://fixer.io/ ) --> Handled
        - if the counterparty (Customer) is one of the supported ones --> Handled
        - validate currencies if they are valid ISO codes (ISO 4217) https://en.wikipedia.org/wiki/ISO_4217 --> Handled

    SPOT, FORWARD: - validate the value date against the product type --> I asked the clarification on this point ,but got no clarification asked to use your own understanding
                    so i have choose the rate field to validate this first find out the rate available on value date as base currency
                    of first pair and then compare the rate of second currency pair available in the rate field on request.
   OPTIONS specific:
    - the style can be either American or European --> Handled
    - American option style will have in addition the excerciseStartDate, which has to be after the trade date
       but before the expiry date - expiry date and premium date shall be before delivery date --> Handled

    2. The validation response should include information about errors detected in the trade
      (in case multiple are detected, all of them should be returned) and in case of bulk validation additional
       linkage between the error and the actual trade --> Handled Returning the validation error with trade linked to that.

====================================================================================================================================================================

PART 3:
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
Unit TEST CASES are covered for all Rules and Service Classes
=====================================================================================================================================================================

rule--
    AmericanOptionStyleCheckRuleTest
    CounterPartyCheckRuleTest
    CurrencyPairISOCheckRuleTest
    CurrencyValueDateCheckRuleTest
    DeliveryDateCheckRuleTest
    OptionStyleCheckRuleTest
    PayCurrencyISOCheckRuleTest
    PremiumCurrencyISOCheckRuleTest
    ProductTypeCheckRuleTest
    SpotOrForwardTypeCheckRuleTest
    ValueDateCheckRuleTest
service--
    TradeValidationServiceTest

=======================================================================================================================================================================

PART 4:
--------------------------------------------------------------------------------------------------------------------------------
Extra activity part description (not mandatory):
=============================================================================================================================

1. Expose performance metrics of the application including: number of requests processed, processing time (min, max, average quantile95)
Solution :Start the Server and we can see metrics on http://localhost:8080/metrics.

2. Describe and present the approach for providing high availability of the service and its scalability
Solution :
    High Availability  : High availability depends on the expected uptime defined for system requirements
           don't be misled by vendor figures. The meaning of having a highly available system and its measurable uptime
           are a direct function of a Service Level Agreement.
           Availability goes up when factoring planned downtime,
           such as a monthly 8-hour maintenance window.
           The cost of each additional nine of availability can grow exponentially.
           Availability is a function of scaling the systems up or out and implementing system, network, and storage redundancy.
    Scalability : Scalability can be achieved by :
                        Horizontal scalability  : It is achieved by introducing load balancer
                                                  of the server on which service is enabled and redistributing the load among
                                                  all the nodes.
                        Vertically scalability :  System on the the service is host scale up the system  by extending its
                                                  Processor power ,main memory and network interfaces to node to
                                                  satisfy more requests per system.
=============================================================================================================================

PART 5:
----------------------------------------------------------------------------------------------------------------------------
Sample test trade data:  Given in the problem statement
============================================================================================================================
[
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "Spot",
    "direction": "BUY",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-15",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "Spot",
    "direction": "SELL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-22",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "SELL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-22",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO2",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "BUY",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-21",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO2",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "BUY",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-08",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUT02",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "BUY",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-08",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO3",
    "ccyPair": "EURUSD",
    "type": "Forward",
    "direction": "BUY",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "valueDate": "2016-08-22",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "EUROPEAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-19",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO2",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "EUROPEAN",
    "direction": "SELL",
    "strategy": "CALL",
    "tradeDate ": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-21",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "EUROPEAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-25",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-19",
    "excerciseStartDate": "2016-08-12",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO2",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "SELL",
    "strategy": "CALL",
    "tradeDate ": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-21",
    "excerciseStartDate": "2016-08-12",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-25",
    "excerciseStartDate": "2016-08-12",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO1",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "BUY",
    "strategy": "CALL",
    "tradeDate": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-19",
    "excerciseStartDate": "2016-08-10",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  },
  {
    "customer": "PLUTO3",
    "ccyPair": "EURUSD",
    "type": "VanillaOption",
    "style": "AMERICAN",
    "direction": "SELL",
    "strategy": "CALL",
    "tradeDate ": "2016-08-11",
    "amount1": 1000000,
    "amount2": 1120000,
    "rate": 1.12,
    "deliveryDate": "2016-08-22",
    "expiryDate": "2016-08-19",
    "excerciseStartDate": "2016-08-10",
    "payCcy": "USD",
    "premium": 0.2,
    "premiumCcy": "USD",
    "premiumType": "%USD",
    "premiumDate": "2016-08-12",
    "legalEntity": "CS Zurich",
    "trader": "Johann Baumfiddler"
  }
]

=======================================================================================================================
Sample Response of the Service created by trade-system using Postman
=======================================================================================================================
{
  "tradeProcessed": 15,
  "tradePassed": 5,
  "tradeFailed": 10,
  "validationResponses": [
    {
      "validationErrors": [
        "Value date against the product type is failed"
      ],
      "linkedTrade": {
        "customer": "PLUTO1",
        "ccyPair": "EURUSD",
        "type": "Spot",
        "direction": "SELL",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1471824000000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Value date against the product type is failed"
      ],
      "linkedTrade": {
        "customer": "PLUTO1",
        "ccyPair": "EURUSD",
        "type": "Forward",
        "direction": "SELL",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1471824000000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Value date cannot fall on weekend or non-working day for currency",
        "Value date against the product type is failed"
      ],
      "linkedTrade": {
        "customer": "PLUTO2",
        "ccyPair": "EURUSD",
        "type": "Forward",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1471737600000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Value date cannot be before trade date",
        "Value date against the product type is failed"
      ],
      "linkedTrade": {
        "customer": "PLUTO2",
        "ccyPair": "EURUSD",
        "type": "Forward",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1470614400000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Value date cannot be before trade date",
        "Value date against the product type is failed",
        "Counterparty (Customer) is not supported"
      ],
      "linkedTrade": {
        "customer": "PLUT02",
        "ccyPair": "EURUSD",
        "type": "Forward",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1470614400000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Value date against the product type is failed",
        "Counterparty (Customer) is not supported"
      ],
      "linkedTrade": {
        "customer": "PLUTO3",
        "ccyPair": "EURUSD",
        "type": "Forward",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": 1471824000000,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": null,
        "strategy": null,
        "deliveryDate": null,
        "expiryDate": null,
        "payCcy": null,
        "premium": null,
        "premiumCcy": null,
        "premiumDate": null,
        "premiumType": null,
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Expiry date and premium date shall be before delivery date "
      ],
      "linkedTrade": {
        "customer": "PLUTO1",
        "ccyPair": "EURUSD",
        "type": "VanillaOption",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": null,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": "EUROPEAN",
        "strategy": "CALL",
        "deliveryDate": 1471824000000,
        "expiryDate": 1472083200000,
        "payCcy": "USD",
        "premium": 0.2,
        "premiumCcy": "USD",
        "premiumDate": 1470960000000,
        "premiumType": "%USD",
        "excerciseStartDate": null
      }
    },
    {
      "validationErrors": [
        "Expiry date and premium date shall be before delivery date "
      ],
      "linkedTrade": {
        "customer": "PLUTO1",
        "ccyPair": "EURUSD",
        "type": "VanillaOption",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": null,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": "AMERICAN",
        "strategy": "CALL",
        "deliveryDate": 1471824000000,
        "expiryDate": 1472083200000,
        "payCcy": "USD",
        "premium": 0.2,
        "premiumCcy": "USD",
        "premiumDate": 1470960000000,
        "premiumType": "%USD",
        "excerciseStartDate": 1470960000000
      }
    },
    {
      "validationErrors": [
        "Excercise Start Date,has to be after the trade date but before the expiry date"
      ],
      "linkedTrade": {
        "customer": "PLUTO1",
        "ccyPair": "EURUSD",
        "type": "VanillaOption",
        "direction": "BUY",
        "tradeDate": 1470873600000,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": null,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": "AMERICAN",
        "strategy": "CALL",
        "deliveryDate": 1471824000000,
        "expiryDate": 1471564800000,
        "payCcy": "USD",
        "premium": 0.2,
        "premiumCcy": "USD",
        "premiumDate": 1470960000000,
        "premiumType": "%USD",
        "excerciseStartDate": 1470787200000
      }
    },
    {
      "validationErrors": [
        "Counterparty (Customer) is not supported"
      ],
      "linkedTrade": {
        "customer": "PLUTO3",
        "ccyPair": "EURUSD",
        "type": "VanillaOption",
        "direction": "SELL",
        "tradeDate": null,
        "amount1": 1000000,
        "amount2": 1120000,
        "rate": 1.12,
        "valueDate": null,
        "legalEntity": "CS Zurich",
        "trader": "Johann Baumfiddler",
        "style": "AMERICAN",
        "strategy": "CALL",
        "deliveryDate": 1471824000000,
        "expiryDate": 1471564800000,
        "payCcy": "USD",
        "premium": 0.2,
        "premiumCcy": "USD",
        "premiumDate": 1470960000000,
        "premiumType": "%USD",
        "excerciseStartDate": 1470787200000
      }
    }
  ]
}
========================================================================================================================
Console Logs of TradeValidationClient
========================================================================================================================

Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date against the product type is failed]---->{"customer":"PLUTO1","ccyPair":"EURUSD","type":"Spot","direction":"SELL","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-22","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date against the product type is failed]---->{"customer":"PLUTO2","ccyPair":"EURUSD","type":"Forward","direction":"SELL","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-22","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date cannot fall on weekend or non-working day for currency, Value date against the product type is failed]---->{"customer":"PLUTO2","ccyPair":"EURUSD","type":"Forward","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-21","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date cannot be before trade date, Value date against the product type is failed]---->{"customer":"PLUTO2","ccyPair":"EURUSD","type":"Forward","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-08","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date cannot be before trade date, Value date against the product type is failed, Counterparty (Customer) is not supported]---->{"customer":"PLUT02","ccyPair":"EURUSD","type":"Forward","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-08","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Value date against the product type is failed, Counterparty (Customer) is not supported]---->{"customer":"PLUTO3","ccyPair":"EURUSD","type":"Forward","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"valueDate":"2016-08-22","legalEntity":"CS Zurich","trader":"Johann Baumfiddler"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Expiry date and premium date shall be before delivery date ]---->{"customer":"PLUTO1","ccyPair":"EURUSD","type":"VanillaOption","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"legalEntity":"CS Zurich","trader":"Johann Baumfiddler","style":"EUROPEAN","strategy":"CALL","deliveryDate":"2016-08-22","expiryDate":"2016-08-25","payCcy":"USD","premium":0.2,"premiumCcy":"USD","premiumDate":"2016-08-12","premiumType":"%USD"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Expiry date and premium date shall be before delivery date ]---->{"customer":"PLUTO1","ccyPair":"EURUSD","type":"VanillaOption","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"legalEntity":"CS Zurich","trader":"Johann Baumfiddler","style":"AMERICAN","strategy":"CALL","deliveryDate":"2016-08-22","expiryDate":"2016-08-25","payCcy":"USD","premium":0.2,"premiumCcy":"USD","premiumDate":"2016-08-12","premiumType":"%USD","excerciseStartDate":"2016-08-12"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Excercise Start Date,has to be after the trade date but before the expiry date]---->{"customer":"PLUTO1","ccyPair":"EURUSD","type":"VanillaOption","direction":"BUY","tradeDate":"2016-08-11","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"legalEntity":"CS Zurich","trader":"Johann Baumfiddler","style":"AMERICAN","strategy":"CALL","deliveryDate":"2016-08-22","expiryDate":"2016-08-19","payCcy":"USD","premium":0.2,"premiumCcy":"USD","premiumDate":"2016-08-12","premiumType":"%USD","excerciseStartDate":"2016-08-10"}
Sep 16, 2017 11:31:56 PM com.cs.codingtest.TradeValidationClient main
INFO: [Counterparty (Customer) is not supported]---->{"customer":"PLUTO3","ccyPair":"EURUSD","type":"VanillaOption","direction":"SELL","amount1":1000000.0,"amount2":1120000.0,"rate":1.12,"legalEntity":"CS Zurich","trader":"Johann Baumfiddler","style":"AMERICAN","strategy":"CALL","deliveryDate":"2016-08-22","expiryDate":"2016-08-19","payCcy":"USD","premium":0.2,"premiumCcy":"USD","premiumDate":"2016-08-12","premiumType":"%USD","excerciseStartDate":"2016-08-10"}


