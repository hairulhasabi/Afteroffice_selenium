# API Automation Testing
Proyek ini berisi **otomatisasi pengujian API** menggunakan Java + Rest Assured + TestNG. Tujuannya untuk memastikan bahwa semua endpoint API bekerja sesuai ekspektasi dan siap untuk integrasi atau produksi.

---

## 🔧 Tech Stack

- Java 17+  
- Maven  
- Rest Assured  
- TestNG  
- JSON

---

## 🚀 Cara Clone Repository

Pastikan kamu sudah mengatur SSH key GitHub. Jika belum, bisa gunakan HTTPS.

### Clone dengan SSH
```bash
git clone git@github.com:hairulhasabi/Afteroffice_selenium.git
```

### Masuk Ke Folder
```bash
cd Afteroffice-selenium
```

### Instalasi Dependency
- Cek Versi Java dan Maven
```bash
java -version
mvn -v
```

- Install semua dependency
```bash
mvn clean install
```

### Menjalankan Test
```bash
mvn test "-DsuiteXml=src/test/resources/cucumber_suite.xml"
```
