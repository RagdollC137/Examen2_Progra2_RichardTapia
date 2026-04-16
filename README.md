# Sistema de Parqueo - Java

## Descripción

Aplicación desarrollada en Java utilizando Swing para la gestión de un parqueo público.
Permite registrar ingresos, salidas y calcular el monto a pagar.

## Tecnologías

* Java
* Swing (JFrame, JTable)
* Apache Ant
* Persistencia en archivos (.txt)

## Arquitectura

El sistema implementa arquitectura por capas:

* Presentación (Interfaces gráficas)
* Lógica de Negocio (Validaciones y cálculos)
* Acceso a Datos (Archivos)
* Entidades

## Funcionalidades

* Registro de ingreso de vehículos
* Registro de salida con cálculo automático
* Visualización de vehículos activos
* Historial de vehículos
* Eliminación de historial

## Reglas de Negocio

* No se permiten placas duplicadas activas
* Validación de datos obligatorios
* Cálculo por hora o fracción (₡700)

## Autor

Richard Tapia Navarro.s
