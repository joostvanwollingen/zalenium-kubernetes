First attempt at running Zalenium on Kubernetes

    kubectl create -f zalenium-deployment.yaml -f zalenium-service.yaml
    
Request 10 webdrivers in parallel
   
    mvn clean install    