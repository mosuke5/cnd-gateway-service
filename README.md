[![Build Status](https://travis-ci.org/mosuke5/cnd-gateway-service.svg?branch=master)](https://travis-ci.org/mosuke5/cnd-gateway-service)

# Freelancer4j gateway service
Freelancer4j gateway service for Appmod Microservices Advanced course.

Implementation: WildFly Swarm

## How to test
```
// Test in local browser
$ mvn clean package
$ java -jar target/api-gateway-service-1.0.0-SNAPSHOT-thorntail.jar -s src/main/resources/project-local.yml
  -> Let's connect localhost:18080
```

## Continuous Integration in Travis CI
This repository is integrated with Travis CI. When you push to github, Travis CI will test automatically.

Current status is here. https://travis-ci.org/mosuke5/cnd-gateway-service

## How to deploy to OpneShift
```
$ export FREELANCER4J_PRJ=<your OpenShift project>

// Edit config to your settings
$ vim etc/project-defaults.yml


// Create configmap for this service
$ oc create configmap api-gateway-service --from-file=etc/project-defaults.yml -n $FREELANCER4J_PRJ

// Deploy this service to OpenShift
$ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$FREELANCER4J_PRJ
```

## Functions
| Method | Endpoint |
|:-----------|:------------|
| GET | /gateway/projects |
| GET | /gateway/projects/{projectId} |
| GET | /gateway/projects/status/{projectStatus} |
| GET | /gateway/freelancers |
| GET | /gateway/freelancers/{freelancerId} |

```
$ export GATEWAY_URL=http://$(oc get route api-gateway-service -n $FREELANCER4J_PRJ -o template --template='{{.spec.host}}')

$ curl -X GET "$GATEWAY_URL/gateway/projects"
[
  {
    "projectId": "329291",
    "ownerFirstName": "Mai",
    "ownerLastName": "Oyama",
    "ownerEmail": "mai.oyama@exmaple.com",
    "projectTitle": "project-1",
    "projectDescription": "project-1-desc",
    "projectStatus": "open"
  },
  {
    "projectId": "329292",
    "ownerFirstName": "Seiko",
    "ownerLastName": "Kotani",
    "ownerEmail": "seiko.kotani@exmaple.com",
    "projectTitle": "project-2",
    "projectDescription": "project-2-desc",
    "projectStatus": "open"
  },
  ...
]

$ curl -X GET "$GATEWAY_URL/gateway/projects/329291"
{
  "projectId": "329291",
  "ownerFirstName": "Mai",
  "ownerLastName": "Oyama",
  "ownerEmail": "mai.oyama@exmaple.com",
  "projectTitle": "project-1",
  "projectDescription": "project-1-desc",
  "projectStatus": "open"
}


$ curl -X GET "$GATEWAY_URL/gateway/projects/status/open"
[
  {
    "projectId": "329291",
    "ownerFirstName": "Mai",
    "ownerLastName": "Oyama",
    "ownerEmail": "mai.oyama@exmaple.com",
    "projectTitle": "project-1",
    "projectDescription": "project-1-desc",
    "projectStatus": "open"
  },
  {
    "projectId": "329292",
    "ownerFirstName": "Seiko",
    "ownerLastName": "Kotani",
    "ownerEmail": "seiko.kotani@exmaple.com",
    "projectTitle": "project-2",
    "projectDescription": "project-2-desc",
    "projectStatus": "open"
  }
]
```

```
$ curl -X GET "$GATEWAY_URL/gateway/freelancers"
[
  {
    "freelancerId": "1",
    "firstName": "Ken",
    "lastName": "Yasuda",
    "email": "ken.yasuda@example.com",
    "skills": [
      "ruby",
      "php",
      "mysql"
    ]
  },
  {
    "freelancerId": "2",
    "firstName": "Tadashi",
    "lastName": "Komiya",
    "email": "tadashi.komiya@example.com",
    "skills": [
      "c#",
      "windows",
      "sqlserver"
    ]
  },
  {
    "freelancerId": "3",
    "firstName": "Taro",
    "lastName": "Goto",
    "email": "taro.goto@example.com",
    "skills": [
      "ruby",
      "postgresql",
      "java"
    ]
  }
]


$ curl -X GET "$GATEWAY_URL/gateway/freelancers/1"
{
  "freelancerId": "1",
  "firstName": "Ken",
  "lastName": "Yasuda",
  "email": "ken.yasuda@example.com",
  "skills": [
    "ruby",
    "php",
    "mysql"
  ]
}
 ```