package com.example.opensourcefinal

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.example.opensourcefinal.MangoActivity
import com.example.opensourcefinal.memoDataModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener


class memoFragment : Fragment() {
    val memodateModelList = mutableListOf<memoDataModel>()  //안에 넣을 리스트


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        //**************************************//
//        //데이터베이스에 저장했으니 이제 값 불러와서 리스트 뷰로 보여주기
//        val database = Firebase.database
//        val myRef = database.getReference("memo")
//
//        val listview = view!!.findViewById<ListView>(R.id.LV)
//        val adapter_list = memoListViewAdapter(memodateModelList)
//        listview.adapter = adapter_list
//
//        myRef.child(Firebase.auth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                //snapshot이란 부분이 모든 데이터를 가져온다.
//                memodateModelList.clear()
//                for(dataModel in snapshot.children){
//                    //snapshot안에 있는 데이터를 하나하나씩 꺼내와서 해야할 행위 시작
//                    //잘가져온 데이터를 ListView에 넣을꺼야
////                    memoDataModelList.add(dataModel.getValue(memodateModel::class.java)!!)
//                    memodateModelList.add(dataModel.getValue(memoDataModel::class.java)!!)
//                    //받아온 데이터가 내가 만든 데이터모델 형태로 알아서 잘 들어가! 즉 들어온 데이터를 리스트에 넣는 상황
//                }
//                adapter_list.notifyDataSetChanged()  //이 부분을 통해 데이터베이스에 데이터 추가되면 리스트뷰에도 새롭게 추가해줘라!라고 명령함
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//
//
//
//
//
//
//        //memoFragment에는 작성한 메모를 띄울꺼야.   간단하게 리스트뷰로 하자
//        val writeBtn = view!!.findViewById<ImageView>(R.id.writeBtn)
//        writeBtn.setOnClickListener {
//            //클릭 시 다이얼로그 로딩
//            val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null)
//            val mBuilder = AlertDialog.Builder(context).setView(mDialogView).setTitle("그대와 함께")
//            //화면 보여주고,
//
//            //이제 mBuilder 안의 버튼 클릭 시 이벤트 처리기능!
//            val mAlertDialog = mBuilder.show()
//
//            val DateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelect)
//            var dateText = ""   //메모 내용 저장을 위해
//            DateSelectBtn.setOnClickListener {
//                //날짜 입력하세요 클릭 시
//                val today = GregorianCalendar()
//                val year : Int = today.get(Calendar.YEAR)
//                val month : Int = today.get(Calendar.MONTH)
//                val day : Int = today.get(Calendar.DATE)
//                val dlg = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener{
//                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                        //다이얼로그 OK 선택시.
//                        DateSelectBtn.setText("${year}, ${month+1}, ${dayOfMonth}")
//                        dateText = "${year}, ${month+1}, ${dayOfMonth}" //메모 입력시 저장
//                    }
//
//                }, year, month, day)
//                dlg.show()
//            }
//            //날짜 선택까지 헀으니 작성한 메모 데이터베이스에 저장시키기
//            val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)
//            saveBtn.setOnClickListener {
//                //저장하기 버튼 클릭시 메모 값과 날짜 값 가져와서 데이터베이스에 저장시켜야함.
//                val memo = mAlertDialog.findViewById<EditText>(R.id.Memo).text.toString()
//
//                val database = Firebase.database
//                val myRef = database.getReference("memo")
//
//                val model = memoDataModel(dateText, memo) //날짜와 메모 순으로 넣어준 후
//                //이 값을 전해줘
//
//                myRef.push().setValue(model)
//                //저장하기 버튼 모두 수행 후 다이얼로그 꺼줘야함
//                mAlertDialog.dismiss()   //꺼줌
//            }
//        }
//
//
//




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_memo, container, false)




        //데이터베이스에서 불러오는 부분에서 안불러와짐.
        //**************************************//
        //3. 데이터베이스에 저장했으니 이제 값 불러와서 리스트 뷰로 보여주기
        var database = Firebase.database
        var myRef = database.getReference("DateAndMemo")

        val listview = view.findViewById<ListView>(R.id.LV)   //리스트뷰 가져와서
        val adapter_list = memoListViewAdapter(memodateModelList)   //어댑터 연결
        listview.adapter = adapter_list
        //리스트뷰 연결



        //리스트뷰 롱 클릭시 삭제
        listview.setOnItemLongClickListener(OnItemLongClickListener { parent, v, position, id ->




            memodateModelList.removeAt(position)
            Toast.makeText(context, "삭제했습니다!", Toast.LENGTH_SHORT).show()
            adapter_list.notifyDataSetChanged()
            true
        })



        //내 uid에 해당하는 데이터베이스 저장 값 불러옴.
        myRef.child(Firebase.auth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //snapshot이란 부분이 모든 데이터를 가져온다.
                memodateModelList.clear()

                for(dataModel in snapshot.children){
                    Log.d("memotext", dataModel.toString())

                    //snapshot안에 있는 데이터를 하나하나씩 꺼내와서 해야할 행위 시작
                    //잘가져온 데이터를 ListView에 넣을꺼야
//                    var msg : String = dataModel.getValue(memoDataModel::class.java).toString()
                    //snapshot에서 받아온 데이터 모델 넣어주면 됨
                    memodateModelList.add(dataModel.getValue(memoDataModel::class.java)!!)//받아온 데이터가 알아서 잘 들어감.
                    //넣은걸 표시해야???


                    //받아온 데이터가 내가 만든 데이터모델 형태로 알아서 잘 들어가! 즉 들어온 데이터를 리스트에 넣는 상황
                }
                Log.d("dataModel", memodateModelList.toString())
                adapter_list.notifyDataSetChanged()  //이 부분을 통해 데이터베이스에 데이터 추가되면 리스트뷰에도 새롭게 추가해줘라!라고 명령함
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })




        var dateText = ""    //메모 저장용 스트링 변수

        //memoFragment에는 작성한 메모를 띄울꺼야.   간단하게 리스트뷰로 하자
        val writeBtn = view.findViewById<ImageView>(R.id.writeBtn)   //작성 아이콘 클릭 시 다이얼로그 띄우자
        writeBtn.setOnClickListener {
            //클릭 시 다이얼로그 로딩
            //한글 입력 자판으로 변경됨.
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView).setTitle("그대와 함께")
            //화면 보여주고,

            //이제 mBuilder 안의 버튼 클릭 시 이벤트 처리기능 수행
            val mAlertDialog = mBuilder.show()   //화면 띄워주고

            val DateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelect)
//            var dateText = ""   //메모 내용 저장을 위해
            DateSelectBtn.setOnClickListener {     //날짜 선택 버튼 클릭 시.
                val today = GregorianCalendar()
                val year : Int = today.get(Calendar.YEAR)
                val month : Int = today.get(Calendar.MONTH)
                val day : Int = today.get(Calendar.DATE)
                val dlg = DatePickerDialog(view.context, object : DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        //다이얼로그 OK 선택시.
                        Log.d("date", "${year}, ${month+1}, ${dayOfMonth}")
                        dateText = "${year} ${month+1} ${dayOfMonth}" //메모 입력시 저장

                        DateSelectBtn.setText("${year} ${month+1} ${dayOfMonth}")     //날짜 선택 시 날짜 표시해줌.
                    }

                }, year, month, day)
                dlg.show()
            }
            //날짜 선택까지 헀으니 작성한 메모 데이터베이스에 저장시키기
            val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)   //저장하기.
            saveBtn.setOnClickListener {
                //저장하기 버튼 클릭시 메모 값과 날짜 값 가져와서 데이터베이스에 저장시켜야함.
                val memo = mAlertDialog.findViewById<EditText>(R.id.Memo).text.toString()

//                val database = Firebase.database
//                val myRef = database.getReference("DateAndMemo")

                database = Firebase.database
                myRef = database.getReference("DateAndMemo").child(Firebase.auth.currentUser!!.uid)

                val model = memoDataModel(dateText, memo) //날짜와 메모 순으로 넣어준 후
                //이 값을 전해줘
                Log.d("text", memo)
                Log.d("date", dateText)    //메모 내용하고 날짜 한번 보자.

                myRef.push().setValue(model)
//                listview.invalidate()
                //여기까지 문제 없음

                //새로운 액티비티로 이동해서 해보자
                //저장하기 버튼 모두 수행 후 다이얼로그 꺼줘야함
                mAlertDialog.dismiss()   //꺼줌
//                val intent = Intent(context, memoActivity::class.java)
//                startActivity(intent)
            }
        }





        //웹뷰를 이용해서 서울맛집, 인기 맛집 보여주기
        val mangoBtn = view.findViewById<Button>(R.id.MangoBtn)
        mangoBtn.setOnClickListener {
            //망고 버튼 클릭 시 새로운 창으로 가서 웹뷰 연결하자
            val intent = Intent(context, MangoActivity::class.java)
            startActivity(intent)
            //여기 액티비티로 가서 수행하자.
        }
        return view.rootView
    }


}