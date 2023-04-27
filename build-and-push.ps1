#    .\build-and-push.ps1
docker build -t word-cloud-worker:latest .
docker tag word-cloud-worker estken/word-cloud-worker
docker push estken/word-cloud-worker