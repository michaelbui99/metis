# Concept
- A JSON rules engine that emits events based on user-defined conditions.
- The engine takes JSON as input and evaluates the JSON input against a set of rules that each consists of a set of conditions.
- Each rule will have a event that it raises in case all the rule's condition's are evaluated to true.


## Rules syntax

### Examples
#### Raise event on condition
```
RULE cheap_meat
WHEN ($.product.discount > 0.5 AND $.product.category == "meat" AND $.product.is_expired == false)
 THEN RAISE buy_product( msg="it's so cheap" )
```
In the example above, we raise the event `buy_product` with a single parameter `msg` with the value of `it's so cheap`.

#### Alias JSON values using bindings
```
RULE mega_discount_on_70_percent
WITH (price=$.product.price, name=$.product.name, discount=$.product.discount ) 
WHEN ($discount > 0.7)
THEN RAISE mega_discount(
    name=$name,
    discount=$discount,
    new_price=$price,
    msg="MEGA DISCOUNT (${discount}) ON ${name}. NEW PRICE: ${price}"
)
```
In the example above we create bindings (aliases) in the `WITH` clause. The bindings can be used in the conditions (`WHEN` clause)
and as the value (right side) in the event params.

#### Boolean AND / OR
```
RULE monitor_meat_discount
WHEN ($.product.category == "meat" AND ($.product.discount > 0.7 OR $.product.clearance == true) AND !$.product.isExpired)
THEN RAISE meat_discount_alert(
    name=$.product.name,
    discount=$.product.discount,
    new_price=$.product.price
)
```
In the example above, when you input the following input: 
```json
{
  "product": {
    "category": "meat",
    "discount": 0.75,
    "clearance": false,
    "isExpired": false,
    "price": 3.99,
    "name": "Organic Beef Steak"
  }
}
```

The engine will return the following events:
```json
[
  {
    "id": "034cce5b-286c-4239-b3aa-bdc13381bf0f",
    "name": "meat_discount_alert",
    "params": {
      "name": "Organic Beef Steak",
      "discount": 0.75,
      "new_price": 3.99
    },
    "timestamp": "2025-07-16T15:14:26.853649+02:00"
  }
]
```
