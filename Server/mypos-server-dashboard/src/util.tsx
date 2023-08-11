
function numToCurrencyString(amt: bigint) {
    const dollars = amt / 100n;
    const cents = amt % 100n;
    const formattedStr = `$${dollars.toLocaleString("en-US")}.${cents.toString().padStart(2, "0")}`;
    return formattedStr;
}

function currencyStringToNum(str: String) {
    const strArr = str.split("");
    const allDigits = strArr.reduce((acc, curr) => {
        if(!Number.isNaN(Number(curr))) acc += curr;
        return acc;
    }, "");
    return BigInt(allDigits);
}

export {
    numToCurrencyString,
    currencyStringToNum,
}