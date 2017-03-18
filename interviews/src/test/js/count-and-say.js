
/*
 The count-and-say sequence is the sequence of integers beginning as follows:

 1, 11, 21, 1211, 111221, ...

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.

 Given an integer n, generate the nth sequence.
 */
function countAndSay(nth) {

    function getChunks(unchunked) {
        var chunks = [];
        var currChunk = unchunked[0];
        var currChar = currChunk;
        for (i=1; i< unchunked.length;i++){
            if (unchunked[i] == currChar) {
                currChunk += unchunked[i];
            } else {
                //change currChunk and currChar
                chunks.push(currChunk);
                currChunk = unchunked[i];
                currChar = currChunk;
            }
        }
        chunks.push(currChunk);
        return chunks;
    }

    function translatedChunk(chunk)  {
        var chars = chunk.split("");
        var first = chars[0];
        var i=1;
        var accum = 1;
        for (i=1;i < chars.length;i++) {
            if (chars[i] == first) {
                accum += 1;
            }
        }
        return accum + first;

    }

    var returnValue = "";
    console.log("chunks are: " + getChunks(nth));

    var j=0;
    var chunks = getChunks(nth);
    for (j=0;j< chunks.length;j++) {
        returnValue += translatedChunk(chunks[j]);
    }
    return returnValue;

};

function generateNext(n){
    var first = "1";
    var arr = [first];
    var i;
    for (i=0;i<n-1;i++) {
        var next = countAndSay(arr[i].toString());
        arr.push(next)
    }
    return arr;
}

var result = generateNext(5);
console.log(result + " should be: 1, 11, 21, 1211, 111221");
//var result = countAndSay("1");
//console.log(result + " should be 11");
//
//result = countAndSay("11");
//console.log(result + " should be 21");
//
//result = countAndSay("111");
//console.log(result + " should be 31");
//
//var result = countAndSay("112");
//console.log(result + " should be 2112");
//
//result = countAndSay("122");
//console.log(result + " should be 1122");
//
//result = countAndSay("1122333");
//console.log(result + " should be 212233");
//
//result = countAndSay("21");
//console.log(result + " should be 1211");