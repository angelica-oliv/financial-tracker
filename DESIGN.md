---
version: alpha
name: "Financial Tracker Design System"
colors:
  primary: "#1A73E8"
  on-primary: "#FFFFFF"
  secondary: "#1E8E3E"
  on-secondary: "#FFFFFF"
  error: "#D93025"
  on-error: "#FFFFFF"
  surface: "#F8F9FA"
  on-surface: "#202124"
  positive: "#1E8E3E"
  negative: "#D93025"
  neutral: "#70757A"
typography:
  fontFamily: "Inter"
  h1:
    fontSize: "2.5rem"
    fontWeight: 700
    lineHeight: 1.2
  body-md:
    fontSize: "1rem"
    lineHeight: 1.5
spacing:
  scale: [0, 4, 8, 12, 16, 24, 32, 48, 64]
  container-padding: "{spacing.16}"
rounded:
  sm: "4px"
  md: "12px"
  lg: "24px"
---

# Design DNA: Financial Tracker

This document defines the visual rules and design tokens for the Financial Tracker application. It is designed to be read by both humans and AI agents (via the Stitch MCP server).

## 1. Overview
A modern, precise, and trustworthy financial interface. The "vibe" is clean, high-contrast, and utilitarian, using Material 3 Expressive principles.

## 2. Colors
- **Primary**: Use for main actions and brand identity.
- **Financial Status**: `positive` green for income/gains and `negative` red for expenses/losses.
- **Surface**: Light gray surfaces to reduce eye strain.

## 3. Typography
Use `Inter` or a similar clean sans-serif. Numbers should be legible and well-spaced. Hierarchies should be strictly followed.

## 4. Layout & Spacing
A strict 4dp/8dp grid. Standard horizontal padding for screens is `16dp` (`{spacing.16}`).

## 5. Shapes
- Use `md` (12px) for cards and most interactive components.
- Use `lg` (24px) for bottom sheets and large container overlays.

## 6. Elevation & Depth
Minimal elevation. Use subtle shadows (`1dp` to `3dp`) or surface color shifts to indicate hierarchy.

## 7. Components
- **Buttons**: Rounded corners (`md`), high contrast.
- **Inputs**: Outlined with clear labels and error states.
- **Cards**: Flat or with very subtle borders/shadows.

## 8. Accessibility
Maintain WCAG AA contrast ratios. Ensure touch targets are at least `48dp`.

## 9. Do's and Don'ts
- **DO**: Use `positive` and `negative` semantic colors consistently for money values.
- **DO**: Use `Long` (cents) in the domain, but map to formatted strings in the UI.
- **DON'T**: Use gradients in data visualizations unless representing a range.
- **DON'T**: Use low-contrast text on surfaces.
