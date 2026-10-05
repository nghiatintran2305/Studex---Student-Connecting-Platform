import SystemStatus from '../components/SystemStatus.jsx';

// Trang khởi tạo tạm thời trước khi có yêu cầu nghiệp vụ.
export default function HomePage() {
  return (
    <main className="container">
      <span className="badge">STUDEX / KHỞI TẠO</span>
      <h1>Nền tảng kết nối sinh viên</h1>
      <p className="intro">Bộ khung React, Spring Boot và PostgreSQL đã sẵn sàng để phát triển.</p>
      <SystemStatus />
    </main>
  );
}
