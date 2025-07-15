TODO: Reference file in docs folder instead
# Concept
- A JSON rules engine that emits events based on user-defined conditions.
- The engine takes JSON as input and evaluates the JSON input against a set of rules that each consists of a set of conditions.
- Each rule will have a event that it raises in case all the rule's condition's are evaluated to true.


## Rules syntax

### Examples
#### Raise event on condition
```
RULE cheap_meat
WHEN ($.product.discount > 0.5 AND $.product.category="meat" AND $.product.is_expired=false)
 THEN RAISE buy_product( msg="it's so cheap" )
```
In the example above, we raise the event `buy_product` with a single parameter `msg` with the value of `it's so cheap`.

#### Using data from the input as params in the event
```
RULE mega_discount_on_70_percent
WITH (price=$.product.price, name=$.product.name, discount=$.product.discount ) 
WHEN ($.product.discount > 0.7)
THEN RAISE mega_discount(
    name=$name,
    discount=$discount,
    new_price=$price,
    msg="MEGA DISCOUNT ($discount) ON $name. NEW PRICE: $price"
)
```
#### Boolean AND / OR
```
RULE monitor_meat_discount
WITH (price=$.product.price, name=$.product.name, discount=$.product.discount ) 
WHEN ($.product.category == "meat" AND ($.product.discount > 0.7 OR $.product.clearance == true) AND !$.product.isExpired)
THEN RAISE meat_discount_alert(
    name=$name,
    discount=$discount,
    new_price=$price,
    msg="MEGA DISCOUNT ($discount) ON $name. NEW PRICE: $price"
)
```
