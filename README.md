## Test case store
- Main service allow configure an store with all the required initial extensions.
- Modules can be extended by adding more entities which can be configured and persisted in memory repository.
- The system contains 2 main entities to manage the store: Item and Order

### Dependencies
maven, java 12

#### Order
allow the creation of an order by adding and setting in place items

#### Items
allow to configure the store items, those can be of many types and then each item can be composed by other items as options (small, medium, large, N) and extras (extra1, extra2, extraN).

## Services
Main service is call Store which currently have a main implementation, it should be configure by assignation of Currency, Locale and StoreConfig


## Important
- Due to lack of time there is 2 things should be added, items should be able to register quantity and then process the order and item accordingly
- Currently Store.placeOrder(String) it is the used method for tests as per requirement in TEST is INPUT="large coffee with extra milk, small coffee with special roast, bacon roll, orange juice" however in normal situation, it should be used Store.placeOrder(Order) instead
- To run the test execute: mvn clean install
