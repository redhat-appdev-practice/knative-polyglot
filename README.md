# Environment setup

Install Serverless and Camel-k Operators

`oc apply -f ./deploy/operators.yaml`

Deploy operator subscriptions

`oc apply -f ./deploy/operator-subscriptions.yaml`

Create knative-serving project

`oc new-project knative-serving`

Install knative-serving

`oc apply -f ./deploy/knative-serving.yaml`

```
oc get pods
activator-6c4754ff4-bsvrf           1/1       Running   1          58s
activator-6c4754ff4-dzz4d           1/1       Running   0          43s
autoscaler-7b4c46bbb7-dztqf         1/1       Running   0          57s
autoscaler-hpa-7f8b568cd-7vkhz      1/1       Running   0          47s
autoscaler-hpa-7f8b568cd-qjv69      1/1       Running   0          47s
controller-f4cc995d-jh2fw           1/1       Running   0          47s
controller-f4cc995d-tnqnh           1/1       Running   0          53s
kn-cli-downloads-564559968d-sn6kw   1/1       Running   0          63s
webhook-774d975d98-xhfql            1/1       Running   0          55s
```

Create knative-test project

`oc new-project knative-test`

# Node.js

Build node.js app image

`oc new-build nodejs:12~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/node`

Deploy the Knative service

`oc apply -f ./deploy/event-display.yaml`

Test the Knative service

`curl http://event-display-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"`


# C#

Build .NET app image

`oc new-build dotnet:3.1~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/csharp  --to="csharp" --name="csharp"`

Deploy the Knative service

`oc apply -f ./deploy/event-display-csharp.yaml`

Test the Knative service

`curl  http://event-display-csharp-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"`

# Quarkus

Create image stream

`oc apply -f ./deploy/is-quarkus-quickstart-native.yaml`

Create the quarkus build config

`oc apply -f ./deploy/quarkus-build-config.yaml`

Deploy the Knative service

`oc apply -f ./deploy/event-display-quarkus.yaml`

Test the Knative service

`curl http://event-display-quarkus-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com -w  "%{time_starttransfer}\n"`

# vert.x

Build vert.x app image

`oc new-build fabric8/s2i-java~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/vertx  --to="vertx" --name="vertx"`

Deploy the Knative service

`oc apply -f ./deploy/event-display-vertx.yaml`

Test the Knative service

`curl http://event-display-vertx-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com -w  "%{time_starttransfer}\n"`

 # Spring Boot

 Build the Spring Boot app image

 `oc new-build openjdk-8-rhel8:1.1~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/spring  --to="spring" --name="spring"`

Deploy the Knative service

`oc apply -f ./deploy/event-display-spring.yaml`

Test the Knative service

`curl http://event-display-spring-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com -w  "%{time_starttransfer}\n"`

# Camel-k

Create a camelknative namespace

`oc new-project camelknative`

Install the Kamel CLI from https://github.com/apache/camel-k/releases

Deploy and configure the Camel-k integration

`kamel run ./samples/Camel-k/Sample.java --name sample --dependency camel-undertow --env CAMEL_SETBODY="Response received from POD : {{env:HOSTNAME}}"`

Wait for the integration to be ready:

```
 oc get it
NAME      PHASE          KIT                        REPLICAS
sample    Building Kit   kit-bslepn11l893qqtt713g 

...

NAME      PHASE     KIT                        REPLICAS
sample    Running   kit-bslepn11l893qqtt713g   0


oc get pods
NAME                                       READY     STATUS              RESTARTS   AGE
camel-k-kit-bslepn11l893qqtt713g-1-build   0/1       Completed           0          63s
camel-k-kit-bslepn11l893qqtt713g-builder   0/1       Completed           0          2m19s
sample-wnnsf-deployment-84744bfbdd-xpdw5   0/2       ContainerCreating   0          6s

oc get deployment
NAME                      READY     UP-TO-DATE   AVAILABLE   AGE
sample-wnnsf-deployment   0/1       1            0           9s

oc get ksvc      
NAME      URL                                                                             LATESTCREATED   LATESTREADY    READY     REASON
sample    http://sample-camelknative.apps.cluster-mta-755a.mta-755a.example.opentlc.com   sample-wnnsf    sample-wnnsf   Unknown   

```

Test the Knative service

`curl http://sample-camelknative.apps.cluster-mta-755a.mta-755a.example.opentlc.com/test  -w  "%{time_starttransfer}\n"`