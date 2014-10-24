val listy = List(0.0,0.0)
val list2= listy :+ 5.6
var thrifty:List[Double] = Nil :: List(2.4)
val strings=List("first\tsecond\tthe one with the ipad\tthe other ones","first\tsecond\tthe one with the opad\tthe other ones","first\tddsecond\tthe one with the ipad\tthe other ones")
val out = for (lister <- strings.map(x => x.split("\t").toList)) yield lister(2)
"iPad".toLowerCase

