@file: JvmName("ToolText")
@file:Suppress("unused")


package com.zhuzichu.shared.tool

fun String?.toStringEmpty(): String {
    return this ?: ""
}

/**
 * 判断是否为空
 *
 * @param str 传入的字符串
 * @return true:为空
 */
fun isEmptyOrNull(str: String?): Boolean = str.isNullOrEmpty()

/**
 * 判断字符串是否 非空
 *
 * @param str 传入的字符串
 * @return true:传入的字符串不为空
 */
fun isNotEmpty(str: String?): Boolean = !isEmptyOrNull(str)

/**
 * 判断字符串中是否含有指定的文案
 *
 * @param str       被检测的字符串
 * @param searchStr 指定的文案
 * @return true: 字符串中是否含有要查找的字符串
 */
fun contains(str: String?, searchStr: String?): Boolean =
    !str.isNullOrEmpty() && searchStr != null && str.contains(searchStr)

/**
 * 去除字符串左右空格
 *
 * @param str 传入的字符串
 * @return 去除左右空格后的字符串
 */
fun trim(str: String?) = str?.trim()

/**
 * 获取字符串的长度
 *
 * @param str 字符文本
 * @return str的长度
 */
fun length(str: String?): Int = str?.length ?: 0

/**
 * 忽略大小写后判断字符串是否相等
 *
 * @param str1 要比较的两字符串的其中一个
 * @param str2 要比较的两字符串的另一个
 * @return true：忽略大小写后，两字符串相等
 */
fun equalsIgnoreCase(str1: String?, str2: String?): Boolean = str1.equals(str2, true)

/**
 * 根据传入的拆分字符，拆分字符串
 * 注意: 判断条件顺序不可变更
 *
 * 若替换工具类,请谨慎测试。
 * java和kotlin 当切割的字符处于字符串末尾 或切割的字符为零宽度字符("")有差异，详见测试用例
 *
 * @param textStr  字符串
 * @param splitStr 拆分字符
 * @return 拆分后的字符串数组
 */
fun split(textStr: String?, splitStr: String?): Array<String>? {
    if (textStr.isNullOrEmpty()) {
        return null
    }
    if (splitStr == null) {
        return arrayOf(textStr)
    }

    textStr.split(splitStr).toMutableList().let {
        when {
            //java切割空串情况  首尾为空时 去空
            "" == splitStr && it.isElementEmpty(0)
                    && it.isElementEmpty(it.lastIndex) -> {
                it.removeAt(it.size - 1)
                it.removeAt(0)
            }
            //切割后全部为空元素情况
            it.isAllEmptyElement() -> return emptyArray()
            //java末尾为切割字符情况
            textStr.endsWith(splitStr) && it.isElementEmpty(it.lastIndex) ->
                it.removeAt(it.size - 1)
        }
        return it.toTypedArray()
    }
}

/**
 * 集合中所有元素是否为""空串
 */
fun MutableList<String>.isAllEmptyElement() = filter { it.isEmpty() }.size == size

/**
 * 对应位置元素是否为空
 * @param index 索引
 */
fun MutableList<String>.isElementEmpty(index: Int) = getOrNull(index).isNullOrEmpty()

/**
 * 将字符串首字母转为大写
 *
 * @return 首字母大写后的字符串，若传入的字符串去掉首末空格后为空，则返回传入的字符串
 */
fun String?.upperFirstLetter(): String? {
    val trim = trim(this)

    if (trim.isNullOrEmpty()) {
        return this
    }

    if (!trim[0].isLowerCase()) {
        return trim
    }

    return (trim[0].toInt() - 32).toChar().toString() + trim.substring(1)
}

/**
 * 将字符串首字母转为小写
 *
 * @return 首字母小写后的字符串，若传入的字符串去掉首末空格后为空，则返回传入的字符串
 */
fun String?.lowerFirstLetter(): String? {
    val trimStr = trim(this)

    if (trimStr.isNullOrEmpty()) {
        return this
    }

    if (!trimStr[0].isUpperCase()) {
        return trimStr
    }

    return (trimStr[0].toInt() + 32).toChar().toString() + trimStr.substring(1)
}

/**
 * 统计fullStr中包含searchStr的个数，默认返回0
 *
 * @param fullStr   长字符串
 * @param searchStr 要统计的短字符串
 * @return count  含有的个数
 */
fun countStr(fullStr: String?, searchStr: String?): Int {

    if (!contains(fullStr, searchStr) || searchStr.isNullOrEmpty()) {
        return 0
    }
    var count = 0
    var index: Int
    var temp = fullStr!!

    while (temp.indexOf(searchStr).also { index = it } > -1) {
        count++
        if (temp.length > index + searchStr.length) {
            // fullStr最后几位恰好为searchStr时，此时index + searchStr.length() = temp.length
            // ，此时substring不会抛索引越界，但建议过滤
            temp = temp.substring(index + searchStr.length)
        } else {
            break
        }
    }

    return count
}

fun String.reversalEvery2Charts(hasSpace: Boolean = false): String {
    val hex = this.addSpaceEvery2Charts()
    return hex.split(" ").reversed().joinToString(if (hasSpace) " " else "")
}

fun String.addSpaceEvery2Charts(): String {
    val hex = this.replace(" ", "")
    val sb = StringBuilder()
    for (i in 0 until hex.length / 2) {
        sb.append(hex.substring(i * 2, i * 2 + 2))
        sb.append(" ")
    }
    return sb.toString().trim()
}

fun String.hex2ByteArray(): ByteArray {
    val s = this.replace(" ", "")
    val bs = ByteArray(s.length/2)
    for (i in 0 until s.length/2){
        bs[i] = s.substring(i*2, i*2+2).toInt(16).toByte()
    }
    return bs
}

fun String.ascii2ByteArray(hasSpace: Boolean = false): ByteArray {
    val s = if(hasSpace) this else this.replace(" ", "")
    return s.toByteArray(charset("US-ASCII"))
}

fun String.addFirst(s: String) = "$s$this"

fun String.addLast(s: String) = "$this$s"