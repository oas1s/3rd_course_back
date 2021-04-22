# Arch System's File Change Project

This project made to upload,share and download files. Requires [authentication service](https://github.com/ARManakhov/3rd_course_auth) and [front-end](https://github.com/AekoArray/arch-front).

## Installation

Clone this git repository using

`git clone https://github.com/oas1s/3rd_course_back.git`

Use the package manager [maven](https://maven.apache.org/) to install application.

``
mvn clean install
``

Then you have to run compiled jar file which located in **target** folder

`java -jar storage-0.0.1-SNAPSHOT.jar`

## Usage

Application will start on localhost:8080 with next endpoints:
* GET /category
* GET /category/{id)
* POST /data
* GET /data/{id}

## Tests

For running tests use maven package manager

`mvn test`

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.