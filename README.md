#First attempt at running Zalenium on Kubernetes

Create Zalenium deployment and expose via service

    kubectl create -f zalenium-deployment.yaml -f zalenium-service.yaml
    
Request 10 webdrivers in parallel
   
    GRID_ADDRESS=http://<your_ip:your_port>/wd/hub mvn clean install    
       