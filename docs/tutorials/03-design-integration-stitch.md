# 🎨 Design Integration: Google Stitch & MCP

This guide explains how to connect this project to **Google Stitch** using the **Model Context Protocol (MCP)**. This setup allows AI agents to "see" your design files and generate production-ready Compose UI that matches the `DESIGN.md` tokens.

## 🌟 Benefits
- **Zero Translation Loss**: AI agents read design tokens directly from `DESIGN.md`.
- **Automated UI Generation**: Generate Composables that follow the project's spacing, color, and typography rules.
- **Design DNA**: Sync changes from a Stitch canvas back to the repository automatically.

## 🛠 Prerequisites
1. **Google Stitch**: Access to [stitch.withgoogle.com](https://stitch.withgoogle.com).
2. **Node.js**: v18 or higher.
3. **Google Cloud CLI**: Installed and authenticated.

## 🚀 Setup Instructions

There are two ways to connect: **Method A** (Recommended for security/OAuth) or **Method B** (Direct HTTP via API Key).

### Method A: Local Proxy (OAuth)
This method uses your Google Cloud credentials and is safer as it doesn't store a raw API key.

1.  **Login to gcloud:**
    ```bash
    gcloud auth login
    gcloud auth application-default login
    ```
2.  **Enable the Stitch API:**
    ```bash
    gcloud beta services mcp enable stitch.googleapis.com
    ```
3.  **Config (Claude Desktop):**
    ```json
    {
      "mcpServers": {
        "stitch": {
          "command": "npx",
          "args": ["-y", "@_davideast/stitch-mcp", "proxy"]
        }
      }
    }
    ```

### Method B: Direct HTTP (API Key)
Fastest setup. Useful for IDEs like **Cursor** or environments where you can't run local background processes.

1. **Get an API Key**: From [stitch.withgoogle.com](https://stitch.withgoogle.com) settings.
2. **Config JSON**:
    ```json
    {
      "mcpServers": {
        "stitch": {
          "type": "http",
          "url": "https://stitch.googleapis.com/mcp",
          "headers": {
            "X-Goog-Api-Key": "YOUR_API_KEY"
          }
        }
      }
    }
    ```

> [!WARNING]
> If using Method B, ensure your configuration file is added to `.gitignore` to avoid leaking your API Key.

### 3. Using the `DESIGN.md` Contract
The `DESIGN.md` file in the root of this project acts as the "source of truth". When working with an AI agent:

- **Command**: *"Update the Dashboard UI using the tokens in DESIGN.md."*
- **Command**: *"Extract the Design DNA from my Stitch project 'Financial Tracker' and update DESIGN.md."*

## 📚 Tools Available via MCP
- `list_projects`: List your Stitch design projects.
- `extract_design_context`: Scans a design screen to update `DESIGN.md`.
- `fetch_screen_code`: Get Compose-compatible logic or design metadata.

---
Next: [Architecture Review & State Management](04-state-management.md) (Coming Soon)
