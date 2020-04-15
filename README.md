# Getting Started
 This project performs CRUD operations on JSON object and compare the JSON objects.
# Run the Project :
 ### Execute - mvn clean install on the root of the project on command prompt
 ### Run the jar file jsoncomparator-0.0.1-SNAPSHOT.jar from /target folder
 ### Execute - java -jar jsoncomparator-0.0.1-SNAPSHOT.jar
 ### Now the server is running
 ### You can listen application on http://localhost:8080/jsoncomparator
# How to execute the API's
  #### Method : POST, URL - http://localhost:8080/jsoncomparator
  #### For this API we need to provide JSON as a request payload 
  #### e.g.
  {
    "Division": {
      "fields": {
        "deptId": {
          "name": "deptId",
          "label": "Department ID ",
          "description": "Department Id",
          "dataTypeDefnId": "TEXT",
          "dataTypeDefn": {
            "dataTypeDefnId": "TEXT",
            "name": "Text",
            "parameterDefns": []
          },
          "dataTypeDefnParameters": {},
          "sequence": 3,
          "searchableFlag": false,
          "deletedFlag": false,
          "primaryKeyFlag": false,
          "uniqueKeyFlag": false,
          "nullableFlag": false,
          "tenantId": "pX2e1e12c31",
          "localizedFlag": false
        }
      }
    }
  }

  #### Method : GET - http://localhost:8080/jsoncomparator/{jsonId}
  #### For this API we need to provide {jsonId} as Integer value to get the stored JSON response
  
  #### Method : PUT, URL - http://localhost:8080/jsoncomparator/{jsonId}
  #### For this API we need to provide JSON as a request payload to update and {jsonId} as Integer value of stored JSON 
  #### e.g.
  {
    "Division": {
      "fields": {
        "deptId": {
          "name": "deptId",
          "label": "Department ID ",
          "description": "Department Id",
          "dataTypeDefnId": "TEXT",
          "dataTypeDefn": {
            "dataTypeDefnId": "TEXT",
            "name": "Text",
            "parameterDefns": []
          },
          "dataTypeDefnParameters": {},
          "sequence": 3,
          "searchableFlag": true,
          "deletedFlag": true,
          "primaryKeyFlag": false,
          "uniqueKeyFlag": false,
          "nullableFlag": false,
          "tenantId": "pX2e1e12c31",
          "localizedFlag": false
        }
      }
    }
  }

#### Method : DELETE, URL - http://localhost:8080/jsoncomparator/{jsonId}
#### For this API we need to provide {jsonId} as Integer value to remove the stored JSON.

#### Method : POST, URL - http://localhost:8080/jsoncomparator/compare-jsons
#### For this API we need to provide JSON as a request payload with baseJsonId as {jsonId}
#### e.g.
 {
  "jsonId": 1,
  "inputJson": {
    "Division": {
      "fields": {
        "deptId": {
          "name": "deptId",
          "label": "Department ID ",
          "description": "Department Id",
          "dataTypeDefnId": "TEXT",
          "dataTypeDefn": {
            "dataTypeDefnId": "TEXT",
            "name": "Text",
            "parameterDefns": []
          },
          "dataTypeDefnParameters": {},
          "sequence": 3,
          "searchableFlag": false,
          "deletedFlag": false,
          "primaryKeyFlag": false,
          "uniqueKeyFlag": false,
          "nullableFlag": false,
          "tenantId": "pX2e1e12c31",
          "localizedFlag": false
        }
      },
      "name": {
        "name": "name",
        "label": "name",
        "description": "name",
        "dataTypeDefnId": "TEXT",
        "dataTypeDefn": {
          "dataTypeDefnId": "TEXT",
          "name": "Text",
          "parameterDefns": []
        },
        "dataTypeDefnParameters": {},
        "sequence": 2,
        "searchableFlag": false,
        "deletedFlag": false,
        "primaryKeyFlag": false,
        "uniqueKeyFlag": false,
        "nullableFlag": false,
        "tenantId": "pX2e1e12c31",
        "localizedFlag": false
      }
    }
  }
}
