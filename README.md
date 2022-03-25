# Serbia's Registry of Medical Supplies - Data Quality
This is an assignment project template for 3AP candidates that evaluates OOP and data analysis skills of a candidate.
##  Prerequisites :school_satchel:
To fulfil this assignment you will need Java 8 (or higher), Maven, Git and a GitHub Account :octopus:. You can use an IDE or source editor of your choosing.
### Java installation
* To install Java 8 follow these [instructions] (http://www.oracle.com/technetwork/java/javase/downloads/index.html) or see any open source variant.

### Maven installation
* To install maven follow these [instructions](https://maven.apache.org/install.html)
* You will only need two maven commands
  * ```mvn install``` to install the needed dependencies; use ```mvn install -DskipTests``` if you want to skip the tests
  * ```mvn test``` to run the provided test cases

### Git / GitHub setup
* Follow these instructions to [setup Git / GitHub](https://help.github.com/articles/set-up-git/)
* To get you acquainted with Git following site is a good [starting point](https://git-scm.com/book/en/v2/Getting-Started-Git-Basics)

## Requirements :mortar_board:
### Input data
This assignment source :chart_with_upwards_trend: provides two .csv files in the [resource folder](src/main/resources/):
* __msGenericName.csv__
Lists all generic names of medical supplies.
Source: [Generic Names of Human Medical Supplies](https://data.gov.rs/sr/datasets/generichki-nazivi-meditsinskikh-sredstava-u-khumanoj-upotrebi/)
* __msRegistry.csv__
Lists all registered medical supplies.
Source: [Human Medical Supplies](https://data.gov.rs/sr/datasets/registar-upisanikh-meditsinskikh-sredstava-za-khumanu-upotrebu/)

### :information_source: Data quality
* The original data found in the above mentioned links has an issue with data quality.
* The original data was cleaned to some extent and reduced in terms of columns.
* A `generic name` may contain more than 1 `category`, while a `product` has only 1 `primary category`.
* The uniqueness of a medical supply `generic name` is its `name` not the `id` or `category/ies`.
* A `producer/license holder` may appear to be the same for two different `products` (e.g. 3M HEALTH CARE), however, the candidate can assume that the uniqueness is determined by the `id`, not the `name`.

### :memo: Assignment 1
Create a branch from this repository
* Either through the [UI](https://help.github.com/articles/creating-and-deleting-branches-within-your-repository/)
* Or with following commands
  * ```git checkout -b your_branch```
  * ```git push -u origin your_branch```

### :memo: Assignment 2
Analyze the data in the two .csv files and build a model that represents the consolidated data.
* Create the model classes
* Implement the method createModel(...) in `MSApplication` so that it returns your model

:bulb: What is the correlation between the two data sets?

### :memo: Assignment 3
Implement all the other methods in `MSApplication` that throw a `RuntimeException` using your model that you have created in Assignment 2.

### :memo: Assignment 4
Many of the rows found in the registry .csv contain something similar to __Model: x; y; z__ in the `name` of the product.
Think about how you would extract and model this new information and we can discuss it later.

## Hand in the assignment :shipit:
If you are happy with your solution and all provided tests pass, please create a new [Pull request](https://github.com/3AP-AG/candidate-assignment-medical-supplies/compare), but do not merge it to master.
Use the description part of your pull request to explain anything you would want the reviewer to know.
Write a few sentences regarding Assignment 4 as well in the description.


## Questions :question:
If you have any need help or have have any questions at all please don’t hesitate to contact us. We are here to help!

# :tada: Good luck!! :tada:
