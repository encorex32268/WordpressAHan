package lihan.chen.wordpressahan.feature.expandablelist

data class ExpandableState(
    val data : Map<Char, List<User>> = UserGroup.getAllUserDataFromJsonData()
)