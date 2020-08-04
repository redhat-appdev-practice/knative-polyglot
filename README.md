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

oc new-build nodejs:12~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/node

oc apply -f ./deploy/event-display.yaml

curl http://event-display-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"

Node.js app:
3.058152


# C#
 oc new-build dotnet:3.1~https://github.com/deewhyweb/polyglot-knative.git --context-dir=/samples/csharp  --to="csharp" --name="csharp"

 oc apply -f ./deploy/event-display-csharp.yaml

curl  http://event-display-csharp-knative-test.apps.cluster-mta-755a.mta-755a.example.opentlc.com  -w  "%{time_starttransfer}\n"


