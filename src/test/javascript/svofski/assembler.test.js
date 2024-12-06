const fs = require('fs');
const path = require('path');
const assembler = require('../../../main/javascript/svofski/assembler');
const { readFile, record, assertCall, assertAllRecords } = require("./common");

const APP_RESOURCES = "src/main/resources";

function findAsmFiles(dir, fileList = []) {
    const files = fs.readdirSync(dir);
    files.forEach(file => {
        const filePath = path.join(dir, file);
        if (fs.statSync(filePath).isDirectory()) {
            findAsmFiles(filePath, fileList);
        } else if (file.endsWith('.asm')) {
            fileList.push(filePath.substring(APP_RESOURCES.length + "/test/".length));
        }
    });
    return fileList;
}

const files = findAsmFiles(path.join(APP_RESOURCES, "/test/"));

describe.each(files)('%s', (fileName) => {
    const filePath = path.join(APP_RESOURCES, "/test/", fileName);
    const program = readFile(filePath);
    const dir = path.join('src/test/resources/AssemblerTest', path.dirname(fileName));

    test('assemble', () => {
        record(assembler.asm, 'evaluateExpression2', [ 'labels' ]);
        record(assembler.asm, 'resolveNumber');

        let data = assembler.assemble(program);

        assertAllRecords(dir);

        assertCall(path.join(dir, 'memory.json'), data.mem);
        assertCall(path.join(dir, 'hex.json'), data.hex);
        assertCall(path.join(dir, 'gutter.json'), data.gutter);
        assertCall(path.join(dir, 'listing.json'), data.listing);
        assertCall(path.join(dir, 'errors.json'), data.errors);
        assertCall(path.join(dir, 'xref.json'), data.xref);
        assertCall(path.join(dir, 'labels.json'), data.labels);
        assertCall(path.join(dir, 'info.json'), data.info);
    });
});