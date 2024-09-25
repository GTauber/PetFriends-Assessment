#!/bin/bash

build_project() {
  local project_dir=$1
  echo "Building project $project_dir"
  cd $project_dir || exit
  ./gradlew clean build -x test || { echo "Build failed for $project_dir"; exit 1; }
  cd ..
}

docker_build() {
  local image_name=$1
  local project_dir=$2
  echo "Building Docker image $image_name for project $project_dir"
  cd $project_dir || exit
  docker build -t $image_name . || { echo "Docker build failed for $image_name"; exit 1; }
  cd ..
}

echo "Building all projects with Gradle..."

build_project "PetFriendsOrders"
build_project "PetFriends_Inventory"
build_project "PetFriendsTransport"

echo "All projects built successfully."

echo "Building Docker images..."

docker_build "petfriends-order" "PetFriendsOrders"
docker_build "petfriends-inventory" "PetFriends_Inventory"
docker_build "petfriends-transport" "PetFriendsTransport"

echo "All Docker images built successfully."


echo "Starting services with Docker Compose..."
cd Docker/PetFriends-AT || { echo "Failed to change directory to Docker/"; exit 1; }
docker-compose -f compose.yaml up --build
