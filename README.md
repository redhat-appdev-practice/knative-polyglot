Install Serverless Operator

oc apply -f ./deploy/operators.yaml

Deploy operator subscriptions

oc apply -f ./deploy/operator-subscriptions.yaml

Create knative-serving project

oc new-project knative-serving

Install knative-serving

oc apply -f ./deploy/knative-serving.yaml

```
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

oc new-project knative-test

export QUAY_USERNAME=

podman login quay.io

podman build -t quay.io/$QUAY_USERNAME/knative-polyglot-nodejs:v1.0 ./samples/node

podman push quay.io/$QUAY_USERNAME/knative-polyglot-nodejs:v1.0

oc apply -f ./deploy/event-display.yaml

curl http://event-display-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"

Node.js app:
3.058152


# C#
https://knative.dev/docs/serving/samples/hello-world/helloworld-csharp/

podman build -t quay.io/$QUAY_USERNAME/knative-polyglot-csharp:v1.0 ./samples/csharp

podman push quay.io/$QUAY_USERNAME/knative-polyglot-csharp:v1.0


curl  http://event-display-csharp-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"