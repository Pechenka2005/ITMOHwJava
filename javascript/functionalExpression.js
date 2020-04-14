"use strict"

function abstractFunction(func) {
    return function(...expressions) {
        return function (...values) {
            let result = [];
            for (let expr of expressions) {
                result.push(expr(...values));
            }
            return func(...result);
        }
    }
}


let add = abstractFunction((a, b) => a + b);
let multiply = abstractFunction((a, b) => a * b);
let subtract = abstractFunction((a, b) => a - b);
let divide = abstractFunction((a, b) => a / b);
let avg5 = abstractFunction((...arr) => (arr.reduce((sum, current) => sum + current) / arr.length));
let med3 = abstractFunction((...arr) => {
    arr.sort(function(a, b) {
        return a - b;
    });
    return arr[(arr.length - arr.length % 2) / 2];
});
let negate = abstractFunction((x) => -x);

let cnst = function (x) {
    return function () {
        return x;
    }
};

let variables = ['x', 'y', 'z'];

const variable = name => {
    let index = variables.indexOf(name);
    return (...values) => values[index];
};


let pi = cnst(Math.PI);

let e = cnst(Math.E);

let constants = {
    'pi' : pi,
    'e' : e
};

let operations = {
    '+' : [add, 2],
    '-' : [subtract, 2],
    '/' : [divide, 2],
    '*' : [multiply, 2],
    'med3' : [med3, 3],
    'avg5' : [avg5, 5],
    'negate' : [negate, 1]
};

function parse(expr) {
    let arr = expr.replace(/\s+/g," ").trim().split(' ');
    let resultParse = [];
    for(let i = 0; i < arr.length; i++) {
        if(Object.keys(operations).includes(arr[i])) {
            let operands = [];
            for(let j = 0; j < operations[arr[i]][1]; j++) {
                operands.push(resultParse.pop());
            }
            resultParse.push(operations[arr[i]][0](...(operands.reverse())));
        } else if(variables.includes(arr[i])) {
            resultParse.push(variable(arr[i]));
        } else if(Object.keys(constants).includes(arr[i])){
            resultParse.push(constants[arr[i]]);
        } else {
            resultParse.push(cnst(parseInt(arr[i])));
        }
    }
    return resultParse[0];
}
