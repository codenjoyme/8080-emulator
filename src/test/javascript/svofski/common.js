const fs = require('fs');
const path = require('path');

function replacer(key, value) {
  if (value instanceof Map) {
    return Object.fromEntries(value);
  }
  return value;
}

export function assertCall(fileName, value, silent = true) {
  const testTitle = expect.getState().currentTestName || "unknown";
  const filePath = path.join(__dirname, 'actual', fileName);

  const isJson = value.constructor === Object || value.constructor === Array;
  const actual = isJson ? JSON.stringify(value, replacer, 2) : value;

  let expected = "";
  try {
    const directoryPath = path.dirname(filePath);
    if (!fs.existsSync(directoryPath)) {
      fs.mkdirSync(directoryPath, { recursive: true });
    }

    if (fs.existsSync(filePath)) {
      expected = fs.readFileSync(filePath, "utf-8");
    }
    fs.writeFileSync(filePath, actual, "utf-8");
  } catch (error) {
    console.error(`Failed to read or write the file: ${error}`);
  }
  if (silent) {
    if (actual !== expected) {
      console.log("Test filed: ", testTitle);
    }
  } else {
    expect(actual).toEqual(expected);
  }
  return value;
}
