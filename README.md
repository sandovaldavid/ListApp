# **ListApp**

**ListApp** es una aplicación desarrollada en Kotlin, utilizando Gradle como sistema de construcción. Este proyecto sigue las convenciones organizacionales para proyectos Java y emplea Maven Publish para la distribución de artefactos.

---

## **📋 Requisitos**

- **JDK** 11 o superior  
- **Gradle** 7.0 o superior  

---

## **📂 Estructura del Proyecto**

```plaintext
.gitignore
.gradle/
.idea/
.kotlin/
app/
build/
gradle/
gradle.properties
gradlew
gradlew.bat
local.properties
settings.gradle.kts
```

---

## **⚙️ Configuración del Proyecto**

El proyecto está configurado para usar Kotlin y cumple con las convenciones de desarrollo establecidas para proyectos Java en la organización. Además, se encuentra preparado para publicar artefactos utilizando el plugin `maven-publish`.

### **🔌 Plugins Utilizados**

- **`java-library`**: Proporciona configuraciones estándar para bibliotecas Java.  
- **`maven-publish`**: Permite la publicación de artefactos en repositorios Maven.  
- **`com.myorg.java-conventions`**: Asegura que el proyecto siga las normas organizacionales.  

---

## **📤 Publicación**

La publicación de artefactos se realiza mediante **Maven Publish**, configurado en el archivo `build.gradle.kts` del módulo `app`.  

### Configuración de Publicación:  

```kotlin
publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myOrgPrivateRepo"
            url = uri("build/my-repo")
        }
    }
}
```

---

## **📜 Comandos Principales**

### **Compilar el Proyecto**
Compila el código fuente y genera los artefactos:  
```bash
./gradlew build
```

### **Ejecutar Pruebas**
Ejecuta todas las pruebas automatizadas configuradas en el proyecto:  
```bash
./gradlew test
```

### **Publicar Artefacto**
Publica el artefacto en el repositorio configurado:  
```bash
./gradlew publish
```

---

## **🤝 Contribuciones**

Las contribuciones son bienvenidas. Si deseas colaborar, por favor abre un *issue* para discutir mejoras o envía un *pull request* con tus cambios propuestos.

---

## **📜 Licencia**

Este proyecto está bajo la licencia [MIT](LICENSE), lo que permite su uso, modificación y distribución abierta.  

--- 

**¡Gracias por explorar ListApp! 🚀**