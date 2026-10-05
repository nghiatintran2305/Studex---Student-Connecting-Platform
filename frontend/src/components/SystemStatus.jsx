import { useSystemStatus } from '../hooks/useSystemStatus.js';

// Hiển thị trạng thái kết nối và nút kiểm tra lại.
export default function SystemStatus() {
  const { loading, data, error, refresh } = useSystemStatus();
  return (
    <section className="status-panel" aria-labelledby="status-title">
      <h2 id="status-title">Kết nối hệ thống</h2>
      <div aria-live="polite">
        {loading && <p>Đang kiểm tra backend và PostgreSQL…</p>}
        {error && <p className="error" role="alert">{error}</p>}
        {data && (
          <dl>
            <dt>Backend</dt><dd>{data.application}</dd>
            <dt>PostgreSQL</dt><dd>{data.database}</dd>
            <dt>Java</dt><dd>{data.javaVersion}</dd>
            <dt>Hibernate</dt><dd>{data.hibernateVersion}</dd>
            <dt>Lần kiểm tra</dt>
            <dd>{new Date(data.checkedAt).toLocaleString('vi-VN')}</dd>
          </dl>
        )}
      </div>
      <button type="button" onClick={refresh} disabled={loading}>Kiểm tra lại</button>
    </section>
  );
}
