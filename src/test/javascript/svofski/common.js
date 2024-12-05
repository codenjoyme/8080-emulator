const fs = require('fs');
const path = require('path');

function replacer(key, value) {
  if (value instanceof Map) {
    return Object.fromEntries(value);
  }
  return value;
}

export function readFile(fileName) {
  const filePath = path.join(__dirname, '', fileName);
  try {
    return fs.readFileSync(filePath, "utf-8");
  } catch (error) {
    console.error(`Failed to read the file: ${error}`);
    return "";
  }
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

export let recorder = (() => {
  var data = [];
  var method = null;
  let collect = (input) => {
    let found = data.find((item) => {
      return JSON.stringify(item) === JSON.stringify(input);
    });
    if (!found) {
      data.push(input);
    }
  };
  let decorate = (object, methodName, fields) => {
    method = methodName;
    let old = object[method];
    object[method] = (...args) => {
      let fieldsData = !fields ? null : fields.map(it => ({
        name: it,
        value: JSON.parse(JSON.stringify(object[it]))
      }));

      let result = old.apply(object, args);

      collect({
        input: args,
        fields: fieldsData,
        result: typeof result == 'undefined' ? null : result,
      });
      return result;
    }
  };
  let result = () => {
    return JSON.stringify(data, null, 2);
  };
  return {
    decorate,
    collect,
    result,
    method: () => method,
  };
});

let recs = [];
export let record = function (object, methodName, fields) {
  let rec = recorder();
  rec.decorate(object, methodName, fields);
  recs.push(rec);
}
export let assertAllRecords = function(dir) {
  recs.forEach((rec) => {
    assertCall(dir + 'method/' + rec.method() + '.json', rec.result());
  });
}
