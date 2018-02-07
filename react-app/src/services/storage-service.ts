import { RootState } from './../redux';

const version = process.env.APP_VERSION;
const STORAGE_KEY = `__SERIALIZED_STATE_TREE_v${version}__`;

function saveState(storeState: RootState, storage: Storage): boolean {
  try {
    const serializedState = JSON.stringify(storeState);
    storage.setItem(STORAGE_KEY, serializedState);
    return true;
  } catch (error) {
    throw new Error('store serialization failed');
  }
}

export function loadState(storage: Storage): RootState | null {
  try {
    const serializedState = storage.getItem(STORAGE_KEY);
    if (serializedState == null) {
      return null;
    }
    return JSON.parse(serializedState);
  } catch (error) {
    throw new Error('store deserialization failed');
  }
}

function saveStateInLocalStorage(storeState: RootState): boolean {
  if (!localStorage) {
    return false;
  }
  return saveState(storeState, localStorage);
}

function saveStateInSessionStorage(storeState: RootState): boolean {
  if (!sessionStorage) {
    return false;
  }
  return saveState(storeState, sessionStorage);
}

export function loadStateFromLocalStorage(): RootState | null {
  if (!localStorage) {
    return null;
  }
  return loadState(localStorage);
}

export function loadStateFromSessionStorage(): RootState | null {
  if (!sessionStorage) {
    return null;
  }
  return loadState(sessionStorage);
}

export const localStorageService = {
  saveState: saveStateInLocalStorage, loadState: loadStateFromLocalStorage
};

export const sessionStorageService = {
  saveState: saveStateInSessionStorage, loadState: loadStateFromSessionStorage
};