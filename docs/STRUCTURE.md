# Quy tắc tách cấu trúc Studex

Backend và frontend có dependency, source code, lệnh build và Dockerfile riêng. `compose.yaml` chỉ nối các service khi chạy local.

| Vai trò backend | File hiện có | Trách nhiệm |
| --- | --- | --- |
| Bootstrap | `StudexApplication.java` | Khởi chạy ứng dụng |
| Controller | `controller/SystemController.java` | Nhận HTTP và gọi service |
| Service | `service/SystemService.java` | Kiểm tra kết nối PostgreSQL |
| Response DTO | `dto/response/SystemStatusResponse.java` | Định dạng dữ liệu trả về |
| Entity cơ sở | `entity/BaseEntity.java` | UUID và thời gian audit cho entity tương lai |
| Exception handler | `exception/GlobalExceptionHandler.java` | Chuẩn hóa lỗi database |

| Vai trò frontend | File hiện có | Trách nhiệm |
| --- | --- | --- |
| Entry point | `src/main.jsx` | Gắn React vào HTML |
| Root | `src/App.jsx` | Nối các trang |
| Page | `src/pages/HomePage.jsx` | Bố cục màn hình |
| Component | `src/components/SystemStatus.jsx` | Hiển thị trạng thái |
| Hook | `src/hooks/useSystemStatus.js` | Quản lý vòng đời request |
| API service | `src/services/systemApi.js` | Gọi backend |
| Styles | `src/styles/global.css` | Giao diện cơ bản |

Khi có một chức năng mới, mỗi class/component đặt trong một file phù hợp vai trò. Controller không viết truy vấn database; component hiển thị không tự viết toàn bộ logic gọi API. Entity không dùng trực tiếp làm response DTO.

Comment chỉ viết một dòng trước class, hàm hoặc khối chức năng cần giải thích. Java/JavaScript dùng `//`, YAML/Dockerfile dùng `#`, batch dùng `rem`, CSS/XML dùng cú pháp comment tương ứng nhưng vẫn giữ trên một dòng. Không viết comment cho từng dòng import hay từng câu lệnh đơn giản.

Ví dụ tên file cho chức năng sau này: `StudentController.java`, `StudentService.java`, `StudentRepository.java`, `Student.java`, `StudentRequest.java`, `StudentResponse.java`; frontend tương ứng có `StudentPage.jsx`, `StudentCard.jsx`, `useStudents.js`, `studentApi.js`. Các file ví dụ này chưa được tạo vì chưa có yêu cầu nghiệp vụ.

`mvnw`, `mvnw.cmd` và `.mvn/wrapper/maven-wrapper.properties` là Maven Wrapper chính thức. Chúng tải Maven đúng phiên bản và gọi Maven để build/chạy backend, không chứa nghiệp vụ. Không cần giữ ZIP hay thư mục `starter` tải tạm từ Spring Initializr.
