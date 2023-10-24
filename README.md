# 🛍️🛒 ECart

*"Empower Your **Passion**, Share Your **Creations**"*

## 📃 Descripcion

ECart (Carrito Electronico) es una aplicacion de E-commerce para la compra y venta de productos creados por los usuarios. ECart permite convertir hobbies como Crochet, Origami, Dibujo, etc, en fuentes de ingresos rentable, facilitando la comercializacion de dichos productos misceláneos ofreciendo hosting, exposicion, menejo de tramites y delivery rentable.

Para tener una mejor compresion del producto, a continuacion se presenta un resumen desde 3 ambitos fundamentales:

1. **Analisis**: los usarios pueden crear "tiendas" (Store) virtuales de manera individual o conjunta, en las cuales pueden vender sus creaciones. Ademas, los usarios pueden ser recomendados y/o explorar
por su cuenta otras tiendas. Nuestro modelo de negocios consiste en facilitar el transporte las mercancias. Nuestros Delivery's llegan a los hogares de los vendedores y entregan los paquetes a los destinatarios correspondientes.
2. **Diseño**: por un lado, cada Usuario puede tener o hacer parte de varias tiendas (Store) virtuales, en las cuales se exponen los Product(os). Cada Product a la venta puede tener Coupon(es) y Reviews, que pueden ser usados por usuarios que vayan a comprar los productos. Como cada User puede comprar y vender, todos tienen un ShoppingCart, un historial de compras (Purchases), ordenes de compra
(Order) y pagos opcionalmente fraccionables por cada orden (Payment). Por otro lado, los Admin de la aplicacion, pueden crear repartidores (Delivery) y administrar los recursos monetarios de la compañia.
3. **Implementacion**: la clase abstracta Person es la clase padre de la que heredan User, Delivery y Admin y tienen sus propios metodos dinamicamente ligados. Cada uno tiene metodos para hacer sus funcionalidades (comprar, vender, etc). Todo se realiza mediante la TUI (Textual User Interface) que tiene varios metodos de con sobrecarga (Utils) y constantes en forma de un Enum para los banners (Banners). La mayoria de atributos de cada una de estas clases estan aislados a sus paquetes o a la misma clase, dependiendo del contexto de cada una.

## 💻 Desarrollo

Ecart utiliza Maven como herramiento de gestion del proyecto. Por tanto, es importante leer la siguiente informacion:

### Directorios

1. `src`: codigo fuente
2. `target`: las clases pre-compiladas
3. `bin`: el `.jar` final para producción

### Comandos

1. Probar el codigo
```
$ mvn -q compile exec:java
```

2. Compilar el `.jar`
```
$ mvn package -DskipTests
```

## 🎨 Diseño

El siguiente apartado contiene las clases (incluyendo sus atributos y metodos) y sus roles.

## 🤖 Implementaciones - Conceptos POO

Aqui encontra una de donde y como se implementan los conceptos de POO aprendidos durante el curso.

## 👷 Funcionalidades

Estas son todas las funcionalidades, tanto complejas como "simples", que hacen parte del programa.

## 🧓 Manual de Usario

Guia comprensiva de como usar la aplicacion.


### • Ingresar al programa

Debe ingresar el usuario y contraseña:
```
💁 Username:  (usuario, luego presionar enter)
🔒 Password:  (contraseña, luego presionar enter)
```
Si el usuario y el password son correctos, ingresa al sistema, de lo contrario, saldrá el siguiente mensaje
```
Hmm looks like you don't have an account. Would you like to create one?
                                [yes|no] 👉 (yes)
```
Debe poner la direccion
```
We need your address to be able to register you in the system
Your calle (number from 0 to 100): (# calle)
```
```
We need your address to be able to register you in the system
Your carrera (number from 0 to 100): (# carrera)
```
### • Menu Login
Al guardar se crea la cuenta con el usuario y el password que estableció al inicio y se despliega este menu:
```
(1) 🛍️  Go shopping!
(2) 🏪 Manage your stores
(3) 🗞️  View shopping cart
(4) 🗳️  Suggest prodcuts
(5) 👱 Profile settings
(9) 🚪 Exit
(0) ⤵️  Return

    Option 👉 (aca va la opcion)
```
### (1) 🛍️  Go shopping!
Muestra las tiendas en las que el usuario se ha unido
```
+--------------------+  
|                    |  
|                    |  
| ─▄▀─▄▀     █▄▀▄▀▄█ |    Name: deli postres
|──▀──▀     █░▀░▀░█▄ |    Tag: FOOD
|█▀▀▀▀▀█▄   █░▀░░░█─█|    Reviews: super cool postres
|█░░░░░█─█  █░░░▀░█▄▀|    Description: 🌟 🌟 🌟 🌟 🌟
| ▀▄▄▄▄▄▀▀   ▀▀▀▀▀▀▀ |
|                    |  
|                    |  
+--------------------+  

Please select one stores you would like to view (or press entre to go back)
                (type its name or press entre) 👉 (nombre de la tienda)
```
Se pone el nombre de la tienda para ingresar y ver los productos, o se da enter si se regresar al menu anterior
```
+--------------------+  +--------------------+  +--------------------+  
|                    |  |                    |  |                    |  
|────────▄█▀▄       .|  | ─▄▀─▄▀     █▄▀▄▀▄█ |  | ─▄▀─▄▀     █▄▀▄▀▄█ |
|──────▄██▀▀▀▀▄     .|  |──▀──▀     █░▀░▀░█▄ |  |──▀──▀     █░▀░▀░█▄ |
|────▄███▀▀▀▀▀▀▀▄   .|  |█▀▀▀▀▀█▄   █░▀░░░█─█|  |█▀▀▀▀▀█▄   █░▀░░░█─█|
|──▄████▀▀▀▀▀▀▀▀▀▀▄ .|  |█░░░░░█─█  █░░░▀░█▄▀|  |█░░░░░█─█  █░░░▀░█▄▀|
|▄█████▀▀▀▀▀▀▀▀▀▀▀▀▀▄|  | ▀▄▄▄▄▄▀▀   ▀▀▀▀▀▀▀ |  | ▀▄▄▄▄▄▀▀   ▀▀▀▀▀▀▀ |
|                    |  |                    |  |                    |  
|  Name: postresito  |  |  Name: servilleta  |  |    Name: plato     |  
|    Price: $20.0    |  |    Price: $20.0    |  |    Price: $20.0    |  
|    Quantity: 10    |  |   Quantity: 100    |  |   Quantity: 100    |  
+--------------------+  +--------------------+  +--------------------+

                   Which product would you like to view?                                      
                              (type the name) 👉 (nombre del producto)                                                               
```
Se pone el nombre del producto que se quiere ver












[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/Q_uKBniY)
