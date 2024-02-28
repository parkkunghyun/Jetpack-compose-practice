package org.techtown.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.techtown.jetpack_compose_practice.ui.theme.JetpackcomposepracticeTheme
// surface는 내부에 중첩된 구성요소에 배경 색상을 입힘
// modifier는 상위요소 레이아웃내에서 UI요소가 배치되고 표시되면 동작하는 방식을 알려줌

// Compose의 세가지 기본 표준 레이아웃 요소는 Column, Row, Box
// 만약 크기 설정을 따로 하지 않았거나 크기에 대한 제약사항이 없을 경우 -> 각 너비는 최소를 가짐
//

// Compose앱은 구성가능한 함수를 호출하여 데이터를 UI로 변경함
// 데이터가 변경되면 Compose는 새 데이터로 이러한 함수를 다시 실행하여
// 업데이트 된 ui를 만든다 이를 리컴포지션이라고함

// Compose는 변경된 구성요소만 다시 구성하고 나머지는 건너뛴다
// 그냥 변수를 만들었을때 Recomposition을 트리거 하지않는 이유는 이 변수를 Compose에서 추적을 안해서!
// 컴포저블 내부 상태를 추가하려면 mutableStateOf함수를 사용하기! ->

// State 및 MutableState는 어떤 값을 보유하고 그 값이 변경될때마다 UI 업데이트 트리거하는 인터페이스
// 리포지션간에 상태를 유지하려면 rmember를 사용해서 변경 가능한상태를 기억해야함!
//

// 구성가능한 함수에서 여러함수를 읽거나 수정하는 상태는 공통의 상위항목에 위치해야함 -> 호이스팅
// 사용자가 continue버튼을 눌렀을때 앱에 알리기
// 이때는 이벤트를 전달하기 위해 콜백을 사용!

// LazyColumn과 LazyRow는 RecyclerView와 동일함
// remember함수는 컴포지션에 유지되는 동안에만 작동함, 기기 회전이나 나갔다 들어올시 전부 날라감
// rememberSaveable은 회전이나 프로세스 중단에도 각 상태를 저장해둠!

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposepracticeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(modifier = Modifier.fillMaxSize()) {
        GreetingList()
    }
}

@Composable
fun GreetingList(modifier: Modifier = Modifier, names: List<String> = List(1000){"$it"}) {
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(2.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(names) { name -> Greeting(name = name) }
    }
}

@Composable
fun Greeting(name: String) {

    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .background(color = Color.Cyan, shape = RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween) {

        Column(modifier = Modifier
            .weight(1F)
            .padding(8.dp)) {
            Text(text = "Hello", modifier = Modifier.padding(top = 4.dp))
            Text(text = "$name", modifier = Modifier.padding(vertical = 4.dp))
            if(expanded) {
                Text(text = ("Composem ipsum color sit lazy, "
                        + "padding theme elit, sed do bouncy. ").repeat(4),)
            }
        }

        Button(onClick = { expanded = !expanded}, modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(Color.Cyan),
            ) {
            if(expanded) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "arrowDown")

            } else {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "arrowDown")
            }
        }

    }
}


@Composable
@Preview(widthDp = 320)
fun MyAppPreview() {
    MyApp()
}



