// Gọi API kiểm tra kết nối qua Vite proxy.
export async function fetchSystemStatus(signal) {
  const response = await fetch('/api/v1/system/status', { signal });
  if (!response.ok) {
    throw new Error(`Backend chưa sẵn sàng (HTTP ${response.status}).`);
  }
  return response.json();
}
