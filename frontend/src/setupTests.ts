import '@testing-library/jest-dom';   // matchersを全体で利用可能に
import { vi } from 'vitest';

// fetch をグローバルでモック
globalThis.fetch = vi.fn();
