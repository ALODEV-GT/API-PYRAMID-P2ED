# Pyramid

### Agregar cartas iniciales

POST:
`serverName/Proyecto2ED-1.0/Game/start`

Json:
```bash
{
    "0": "6♣",
    "1": "9♦",
    "2": "3♣",
    "3": "4♦",
    "4": "1♥",
    "5": "8♠",
    "6": "K♥",
    "7": "J♠",
    "8": "Q♣",
    "9": "7♦",
    "10": "K♠"
}
```

### Eliminar cartas
POST:
`serverName/Proyecto2ED-1.0/Game/delete`
```bash
{
    "delete_1": "1♥",
    "delete_2": "Q♣"
}
```

### Insertar carta

POST:
`serverName/Proyecto2ED-1.0/Game/add`
```bash
{
    "insert": "K♣"
}
```

### Obtener estado del arbol 
GET:
`serverName/Proyecto2ED-1.0/Game/status-avltree`

Respuesta:
```bash
{
	"path":serverName/imagenes/arbol.jpg
}
```

### Obtener orden del arbol
GET:
`serverName/Proyecto2ED-1.0/Game/avltree?transversal=preOrder`

Respuesta:
```bash
{
	"0": "9♦",
	"1": "6♣",
	"2": "3♣",
	"3": "4♦",
	"4": "K♣",
	"5": "7♦",
	"6": "J♠",
	"7": "K♥",
	"8": "8♠",
	"9": "K♠"
}
```

### Obtener cartas de un nivel 
GET:
`serverName/Proyecto2ED-1.0/Game/get-level?level=3`

Respuesta:
```bash
{
	"0": "3♣",
	"1": "4♦",
	"2": "K♥",
	"3": "K♠"
}
```
