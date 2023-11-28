# Setup Backend

### Syarat
- Pasang dulu postgres
- Pakai intllij Idea

## Running backend
1. Clone repositorinya, kemudian buka dengan intellijIdea
2. Tunggu proses installasi saat membuka project
3. Jalankan perintah berikut di root project, `psql -U postgres --file rentalapi_db.sql`
4. Setelah itu running aplikasinya, 
5. Caranya yaitu masuk ke `src > main > java > com.proyek... >`
6. Kemudian buka file class `RentalapiApplication`
7. Done!!!

Kalau masih ada yang error, langsung tanya aja.


# Dokumentasi

## Fitur kendaraan

GET :: /api/kendaraan/

example response:

```json
[
    {
    "kendaraanId": 1,
    "namaKendaraan": "Agaya Avansa",
    "tipeKendaraan": "Sedang",
    "hargaSewa": 200,
    "jumlahKetersediaan": 3
    },
    {
    "kendaraanId": 3,
    "namaKendaraan": "Paris Mitsu",
    "tipeKendaraan": "Motor",
    "hargaSewa": 320,
    "jumlahKetersediaan": 3
    },
]
```

GET :: /api/kendaraan/{kendaraanId}

example response:

```json
{
"kendaraanId": 1,
"namaKendaraan": "Agaya Avansa",
"tipeKendaraan": "Sedang",
"hargaSewa": 200,
"jumlahKetersediaan": 3
}
```

POST :: /api/kendaraan (Access by Admin)

request body:

```json
{
  "namaKendaraan": "New Mitsu",
  "tipeKendaraan": "Motor",
  "hargaSewa": 320,
  "jumlahKetersediaan": 3
}
```
example response:

```json
{
"kendaraanId": 1,
"namaKendaraan": "New Mitsu",
"tipeKendaraan": "Motor",
"hargaSewa": 320,
"jumlahKetersediaan": 3
}
```

PUT :: /api/kendaraan/{kendaraanId} (Access by Admin)

request body:

```json
{
  "namaKendaraan": "New Mitsu",
  "tipeKendaraan": "Motor",
  "hargaSewa": 320,
  "jumlahKetersediaan": 3
}
```

example response:

```json
{
  "success": true
}
```

DELETE :: /api/kendaraan/{kendaraanId} (Access by Admin)

example response:

```json
{
  "success": true
}
```


