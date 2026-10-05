import { defineConfig, loadEnv } from 'vite';
import react from '@vitejs/plugin-react';

// Keep browser requests on the same origin; Vite forwards API calls to Spring.
export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), 'VITE_');
  return {
    plugins: [react()],
    server: {
      proxy: {
        '/api': {
          target: env.VITE_API_PROXY_TARGET || 'http://localhost:8088',
          changeOrigin: true,
        },
      },
      // Polling makes bind-mounted source changes visible in Docker Desktop.
      watch: { usePolling: env.VITE_USE_POLLING === 'true' },
    },
  };
});
