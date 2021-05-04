# minikube e kubectl

Primeiro instalar o minikube e o kubectl
[Minikube](https://minikube.sigs.k8s.io/docs/start/)
[kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

Instalar o Istio
[Istio](https://istio.io/latest/docs/setup/getting-started/)

Iniciar o minikube com a versão correta do kubernetes para o istio:
```
$ minikube start --kubernetes-version=v1.20.2
```

Iniciar o tunnel do minikube para ter acesso ao loadbalancer
```
$ minikube tunnel --cleanup
```

Iniciar o istio junto ao kubernetes
```
$ istioctl install --set profile=demo -y
```

Configurar o kubernetes para funcionar com o Istio
```
$ kubectl label namespace default istio-injection=enabled
```

Fazer o deploy da aplicação
```
$ kubectl apply -f deploy.yaml
```

Esperar todos os pods subirem
```
$ kubectl get pods
```

Habilitar o gateway do istio
```
$ kubectl apply -f istio-gateway.yaml
```

Verifique o ip e porta do gateway
```
$ kubectl get svc istio-ingressgateway -n istio-system
```

Pronto, a aplicação já está rodando no ip e porta do gateway.

#Grafana e Prometheus

Instalar o Prometheus
```
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/prometheus.yaml
```

Instalar o Grafana
```
$ kubectl apply -f https://raw.githubusercontent.com/istio/istio/release-1.9/samples/addons/grafana.yaml
```

Verificar se os serviços estão rodando
```
$ kubectl -n istio-system get svc prometheus
```
```
$ kubectl -n istio-system get svc grafana
```

Iniciar o Grafana
```
$ istioctl dashboard grafana
```

Acessar o dashboard do grafana
[Grafana](http://localhost:3000)
