# test-spring-boot-level-1

## PERSONAS JURÍDICAS

Agregar una persona jurídica con los siguientes campos:

	Razón social : MySocialReason
	Año de fundación: 2000
	Cuit: 20426574315
	
  
```
curl -X GET "http://localhost:8080/api/personaJuridica/add?socialReason=MySocialReason&fundationYear=2000&cuit=20426574315"
```

---

Si vuelve a intentarlo cambiando cualquier campo menos el cuit, éste será encontrado en la base y recibirá el error respectivo.

---

intentar agregar una persona jurídica cuya razón social contiene más de cien caracteres [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaJuridica/add?socialReason=1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123&fundationYear=2000&cuit=20426574325"
```

---

Listar todas las personas jurídicas (le recomiendo usarlo para verificar que funcionen los cambios cada vez que agregue/modifique/elimine, aunque igual recibirá mensajes por los results)

```
curl -X GET "http://localhost:8080/api/personaJuridica/findAll"
```

verificar con findAll

---

modificar la persona jurídica del cuit 20426574315

```
curl -X GET "http://localhost:8080/api/personaJuridica/updateByCuit?socialReason=MyNewSocialReason&fundationYear=1999&cuit=20426574315"
```

intentar modificar una persona jurídica inexistente por el cuit 20426574395 [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaJuridica/updateByCuit?socialReason=MyNewSocialReason&fundationYear=1999&cuit=20426574395"
```

---
intentar modificar una persona jurídica por el cuit 20426574315 con una razón social de más de 100 caracteres [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaJuridica/updateByCuit?socialReason=1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123&fundationYear=1999&cuit=20426574315"
```

---

buscar una persona jurídica existente por el cuit 20426574315

```
curl -X GET "http://localhost:8080/api/personaJuridica/findByCuit?cuit=20426574315"
```

---

buscar una persona jurídica inexistente por el cuit 20426574215 [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaJuridica/findByCuit?cuit=20426574215"
```

---

eliminar una persona jurídica inexistente por cuit [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaJuridica/deleteByCuit?cuit=20111111115"
```

---

eliminar una persona jurídica existente por cuit

```
curl -X GET "http://localhost:8080/api/personaJuridica/deleteByCuit?cuit=20426574315"
```

---

eliminar todas las personas jurídicas existentes (le recomiendo agregar alguans a su antojo, con diferente cuit y sin pasarse del limite de caracteres para luego hacer dos findAll, antes y despues del deleteAll)

```
curl -X GET "http://localhost:8080/api/personaJuridica/deleteAll"
```

---


## PERSONAS FÍSICAS


Agregar una persona física con los siguientes campos:
	Dni: 42657431
	Nombre : Edgardo
	Apellido: Alvarez
	Cuit: 20426574315

```
curl -X GET "http://localhost:8080/api/personaFisica/add?dni=42657431&name=Edgardo&surname=Alvarez&cuit=20426574315"
```

---

Si vuelve a intentarlo cambiando cualquier campo menos el cuit, éste será encontrado en la base y recibirá el error respectivo.

---

intentar agregar una persona física cuyo nombre contiene más de ochenta caracteres y el apellido supera los 250 caracteres [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaFisica/add?dni=42657431&name=12345678901234567890123456789012345678901234567890123456789012345678901234567890123&surname=1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123&cuit=20426574385"
```

---

Listar todas las personas físicas (le recomiendo usarlo para verificar que funcionen los cambios cada vez que agregue/modifique/elimine, aunque igual recibirá mensajes por los results)

```
curl -X GET "http://localhost:8080/api/personaFisica/findAll"
```

---

modificar la persona física del cuit 20426574315

```
curl -X GET "http://localhost:8080/api/personaFisica/updateByCuit?dni=42657331&name=Pepe&surname=Argento&cuit=20426574315"
```

---

intentar modificar una persona física inexistente por el cuit 20426574395 [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaFisica/updateByCuit?dni=42657331&name=Pepe&surname=Argento&cuit=20426574395"
```

---

intentar modificar una persona física por el cuit 20426574315 cuyo nombre contiene más de ochenta caracteres y el apellido supera los 250 caracteres [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaFisica/updateByCuit?dni=42657431&name=12345678901234567890123456789012345678901234567890123456789012345678901234567890123&surname=1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123&cuit=20426574315"
```

---

buscar una persona física existente por el cuit 20426574315

```
curl -X GET "http://localhost:8080/api/personaFisica/findByCuit?cuit=20426574315"
```

---

buscar una persona física inexistente por el cuit 20426574215 [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaFisica/findByCuit?cuit=20426574215"
```

---

eliminar una persona física inexistente por cuit [devuelve un error]

```
curl -X GET "http://localhost:8080/api/personaFisica/deleteByCuit?cuit=20111111115"
```

---

eliminar una persona física existente por cuit

```
curl -X GET "http://localhost:8080/api/personaFisica/deleteByCuit?cuit=20426574315"
```

---

eliminar todas las personas físicas existentes

```
curl -X GET "http://localhost:8080/api/personaFisica/deleteAll"
```

---

mi mail es martuslalv@hotmail.com
