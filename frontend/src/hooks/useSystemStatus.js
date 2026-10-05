import { useEffect, useState } from 'react';
import { fetchSystemStatus } from '../services/systemApi.js';

// Quản lý trạng thái tải, lỗi và làm mới kết quả kiểm tra hệ thống.
export function useSystemStatus() {
  const [state, setState] = useState({ loading: true, data: null, error: null });
  const [refreshKey, setRefreshKey] = useState(0);

  useEffect(() => {
    // Cancel obsolete requests on unmount, refresh, or StrictMode remount.
    const controller = new AbortController();
    setState({ loading: true, data: null, error: null });
    fetchSystemStatus(controller.signal)
      .then((data) => {
        if (!controller.signal.aborted) setState({ loading: false, data, error: null });
      })
      .catch((error) => {
        if (!controller.signal.aborted) {
          setState({ loading: false, data: null, error: error.message });
        }
      });
    return () => controller.abort();
  }, [refreshKey]);

  return { ...state, refresh: () => setRefreshKey((value) => value + 1) };
}
