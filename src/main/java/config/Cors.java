package config;

import jakarta.servlet.http.HttpServletResponse;

public class Cors {
    public static void setCorsHeaders(HttpServletResponse resp) {
        // 允许前端源（*表示允许所有，开发环境可用）
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的请求头（必须包含Content-Type，因为前端发JSON）
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        // 允许的请求方法（包含OPTIONS和POST）
        resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        // 允许前端读取的响应头（可选，按需添加）
        resp.setHeader("Access-Control-Expose-Headers", "Content-Type");
        // 预检请求的有效期（秒），避免频繁发送OPTIONS请求
        resp.setHeader("Access-Control-Max-Age", "3600");
    }
}
