# Studex — Student Connecting Platform

Bộ khung dự án gồm **backend** và **frontend** độc lập. Hiện chỉ có API và trang kiểm tra kết nối; chức năng nghiệp vụ sẽ được bổ sung theo yêu cầu sau.

| Thành phần | Phiên bản / cấu hình |
| --- | --- |
| Java trên máy | 21.0.10; backend biên dịch cho Java 21 |
| Spring Boot | 4.1.1 |
| Hibernate | 7.4.5.Final; Spring Boot quản lý qua Spring Data JPA |
| Maven Wrapper | 3.9.16; không cần cài Maven toàn máy |
| Node.js / npm | 24.16.0 / 11.13.0 |
| React / Vite | 19.3.0 / 8.3.2 |
| Docker / Compose trên máy | 29.4.3 / 5.1.3 |
| PostgreSQL / pgAdmin trong Docker | 17.10 (`17-alpine`) / 9.17 |

Spring Boot hỗ trợ Java 21 theo [yêu cầu hệ thống chính thức](https://docs.spring.io/spring-boot/system-requirements.html). Node.js trên máy đáp ứng [yêu cầu Vite](https://vite.dev/guide/). Các dependency frontend được khóa trong `frontend/package-lock.json`.

```text
Studex---Student-Connecting-Platform/
├── backend/               # Java, Spring Boot, Hibernate, Maven, Dockerfile
│   ├── .mvn/wrapper/      # Tải đúng Maven khi máy chưa cài Maven
│   ├── src/main/java/com/studex/
│   │   ├── controller/   # Endpoint HTTP; mỗi controller một file
│   │   ├── service/      # Xử lý chức năng; mỗi service một file
│   │   ├── repository/   # Truy cập dữ liệu qua Spring Data JPA
│   │   ├── entity/       # Ánh xạ JPA; mỗi entity một file
│   │   ├── dto/request/  # Dữ liệu đầu vào API
│   │   ├── dto/response/ # Dữ liệu trả về API
│   │   ├── exception/   # Xử lý lỗi chung
│   │   ├── config/      # Cấu hình ứng dụng
│   │   ├── mapper/      # Chuyển đổi entity và DTO
│   │   ├── enums/       # Kiểu liệt kê
│   │   └── util/        # Hàm tiện ích
│   ├── src/main/resources/
│   │   ├── application.yml
│   │   └── db/migration/ # Chỗ đặt migration; chưa cài công cụ migration
│   └── src/test/java/com/studex/
├── frontend/              # React, Vite, package.json, Dockerfile riêng
│   ├── public/
│   └── src/
│       ├── components/    # Component tái sử dụng
│       ├── pages/         # Các màn hình
│       ├── hooks/         # Logic React dùng lại
│       ├── services/      # Hàm gọi API
│       ├── styles/        # CSS
│       ├── assets/        # Ảnh và tài nguyên
│       ├── context/       # State dùng chung
│       ├── routes/        # Cấu hình điều hướng
│       └── utils/         # Hàm tiện ích frontend
├── docker/pgadmin/        # Đăng ký kết nối PostgreSQL trong pgAdmin
├── docs/                  # Tài liệu dự án và hướng dẫn cấu trúc
├── compose.yaml           # Chạy bốn service cùng nhau
├── .env.example           # Mẫu biến môi trường local
└── dev.cmd                # Lệnh quản lý Docker trên Windows
```

Các thư mục chưa có chức năng dùng `.gitkeep` để được Git lưu lại. Comment trong code là một dòng trước class, hàm hoặc khối chức năng. Không gộp controller, service, entity hoặc DTO vào cùng một class.

**Chạy toàn bộ bằng Docker**

Mở Docker Desktop, rồi chạy tại thư mục gốc:

```powershell
.\dev.cmd start
```

Lệnh tự tạo `.env` từ `.env.example` nếu chưa có, build backend/frontend và chờ các service ứng dụng sẵn sàng. Đây là cấu hình phát triển localhost; frontend chạy Vite dev server và hỗ trợ cập nhật trực tiếp khi sửa `frontend/src`.

| Dịch vụ | Địa chỉ mặc định |
| --- | --- |
| Frontend | http://localhost:5173 |
| Backend API | http://localhost:8088/api/v1/system/status |
| Backend health | http://localhost:8088/actuator/health |
| pgAdmin | http://localhost:5050 |
| PostgreSQL từ máy host | localhost:5432 |

Cổng 8088 được chọn vì 8080 đang được một dự án khác sử dụng. Đổi các biến `*_PORT` trong `.env` nếu cần đổi cổng.

```powershell
.\dev.cmd status
.\dev.cmd logs
.\dev.cmd stop
```

`stop` giữ nguyên dữ liệu PostgreSQL và pgAdmin. Khi sửa Java, chạy lại `start` để build JAR mới. Khi sửa dependency hoặc cấu hình frontend, cũng chạy lại `start` để build image mới.

**Đăng nhập pgAdmin**

Tài khoản mặc định: `admin@studex.dev`; mật khẩu: `studex_admin_2026`. Chọn server **Studex Local PostgreSQL** đã đăng ký sẵn, nhập mật khẩu database `studex_local_2026`.

Kết nối bên trong Docker dùng host `postgres`, port `5432`, database `studex`, user `studex`. Nếu thay tên database hoặc user trong `.env`, cập nhật thêm `docker/pgadmin/servers.json`; với pgAdmin đã có dữ liệu, sửa connection trong giao diện. Biến đăng nhập pgAdmin và tài khoản PostgreSQL khởi tạo chỉ áp dụng khi volume còn trống.

**Chạy backend và frontend trực tiếp trên máy**

Khởi động riêng PostgreSQL/pgAdmin trước:

```powershell
# Bỏ qua dòng copy nếu đã có .env để giữ cấu hình hiện tại.
Copy-Item .env.example .env
docker compose up -d postgres pgadmin
```

Terminal backend:

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Terminal frontend:

```powershell
cd frontend
npm.cmd ci
npm.cmd run dev
```

Không chạy đồng thời container backend/frontend và bản chạy trực tiếp trên cùng cổng. Backend mặc định kết nối database local theo `.env.example`; nếu đổi thông tin database, truyền `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD` vào môi trường terminal Java. Backend không tự đọc file `.env` của Docker Compose.

Nếu đổi cổng backend, tạo `frontend/.env` từ `frontend/.env.example`, sửa `VITE_API_PROXY_TARGET`, rồi khởi động lại Vite. Node.js phục vụ công cụ frontend; backend API sử dụng Java/Spring Boot.

**Kiểm tra build**

```powershell
cd backend
.\mvnw.cmd verify
```

```powershell
cd frontend
npm.cmd run build
```

Chưa tạo bảng nghiệp vụ hoặc migration. Hibernate dùng `ddl-auto: validate`; `BaseEntity` là lớp cha ánh xạ và không tạo bảng riêng. Khi có yêu cầu nghiệp vụ, bổ sung entity, repository, service, DTO, controller, migration và kiểm thử tương ứng. Xem thêm [hướng dẫn đặt file](docs/STRUCTURE.md).
