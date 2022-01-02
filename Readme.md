# Getting started

This repository shows how to upload and download images using Spring Boot, AWS and React.
Axios was used for saving the user profiles into the databse.

<!---
```plantuml
@startuml
skinparam actorStyle awesome
:User: as user
user -> (Frontend): use
(Frontend) -> (API): upload and retrieve images
(API) -> (Service)
(Service) -> (Database): saves profiles
(Service) -> (Bucket): upload and download images
@enduml
```
-->

![PlantUML model](http://www.plantuml.com/plantuml/png/NOzBQiH034JtVGejuuKlq8lWX138Bc1o0CLT2OtxOoJPPcu_D9uFCnkXqdjGQ51ZiJKdftCi2mjduicgZ7PA8DwXDSFv7uLuOgLL8QuDwljNUlcKMWmbTBuHTyy7Vll-QcH9bGDn2IGmYTX0CVC_r3NZuewGBKxet6qxxXzi_CkApfFo1gL5wbzCq6VnVPrcs6DVg7jfuTete8JsxHa0 "Overview")