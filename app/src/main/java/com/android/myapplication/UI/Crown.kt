package com.android.myapplication.UI

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.myapplication.R
import com.android.myapplication.databinding.ActivityMainBinding
import com.android.myapplication.databinding.FragmentCrownBinding


class Crown : Fragment() {

    companion object {

        fun newinstance():Crown = Crown()

    }
    private var _binding: FragmentCrownBinding? = null
    private val binding get() = _binding!!


    var total_Price: Int = 0
    var state18 = false
    var state18_txt = ""

    var state17 = false
    var state17_txt = ""

    var state16 = false
    var state16_txt = ""

    var state15 = false
    var state15_txt = ""

    var state14 = false
    var state14_txt = ""

    var state13 = false
    var state13_txt = ""

    var state12 = false
    var state12_txt = ""

    var state11 = false
    var state11_txt = ""

    var state21 = false
    var state21_txt = ""

    var state22 = false
    var state22_txt = ""

    var state23 = false
    var state23_txt = ""

    var state24 = false
    var state24_txt = ""

    var state25 = false
    var state25_txt = ""

    var state26 = false
    var state26_txt = ""

    var state27 = false
    var state27_txt = ""

    var state28 = false
    var state28_txt = ""

    var state31 = false
    var state31_txt = ""

    var state32 = false
    var state32_txt = ""

    var state33 = false
    var state33_txt = ""

    var state34 = false
    var state34_txt = ""

    var state35 = false
    var state35_txt = ""

    var state36 = false
    var state36_txt = ""

    var state37 = false
    var state37_txt = ""

    var state38 = false
    var state38_txt = ""

    var state41 = false
    var state41_txt = ""

    var state42 = false
    var state42_txt = ""

    var state43 = false
    var state43_txt = ""

    var state44 = false
    var state44_txt = ""

    var state45 = false
    var state45_txt = ""

    var state46 = false
    var state46_txt = ""

    var state47 = false
    var state47_txt = ""

    var state48 = false
    var state48_txt = ""

    //임플란트
    var State_ImpL = false
    var State_ibs = false
    var State_ostem = false

    //Ostem 보철
    var State_CrownOs = false
    var State_ZirOs = false
    var State_PfmOs = false
    var State_MetalOs = false
    //Ibs 보철
    var State_CrownIbs = false
    var State_ZirIbs = false
    var State_PfmIbs = false
    var State_MetalIbs = false

    //보철(Crown)
    var State_Crown = false
    var State_Zir = false
    var State_ZirA = false
    var State_ZirB = false

    var State_Pfm = false
    var State_Metal = false
    var State_GoldCrown = false
    //특수
    var State_GbrA = false //뼈이식(간단)
    var State_GbrB = false //뼈이식(복잡)

    var price_Endo: Int = 0
    var price_Post: Int = 0
    var price_Core: Int = 0

    var price_Cavatiy:Int = 0
    var price_Lesin: Int = 0
    var price_HybridInlay: Int = 0
    var price_EmaxInlay: Int = 0

    var price_Denture:Int = 0
    var price_DentureA:Int = 0
    var price_DentureB:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCrownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 임플란트

        binding.btnImpL.setOnClickListener {



            State_ImpL = if (State_ImpL == false) true else false
            binding.btnImpL.setBackgroundResource(if (State_ImpL == true) R.drawable.type2 else R.drawable.type1)
            if(State_ImpL == true){

                binding.apply {
                    btnIbs.visibility = View.VISIBLE
                    btnOstem.visibility = View.VISIBLE
                    btnGbr.visibility = View.VISIBLE
                    btnGbrB.visibility = View.VISIBLE
                    btnCrown.visibility = View.GONE
                }
            }
            else{
                binding.apply {
                    btnIbs.visibility = View.GONE
                    btnOstem.visibility = View.GONE
                    btnGbr.visibility = View.GONE
                    btnGbrB.visibility = View.GONE
                    btnCrown.visibility = View.VISIBLE
                }
            }

        }
        binding.btnOstem.setOnClickListener {

            State_ostem = if (State_ostem == false) true else false
            total_Price += if (State_ostem == true) 70 else -70
            binding.btnOstem.setBackgroundResource(if (State_ostem == true) R.drawable.type2 else R.drawable.type1)
            if (State_ostem == true)
            {
                binding.apply {
                    btnIbs.visibility = View.GONE
                    btnGbr.visibility = View.GONE
                    btnGbrB.visibility = View.GONE
                    OstemCrownbox.visibility = View.VISIBLE
                }
            }
         else
            {
                binding.apply {
                    btnIbs.visibility = View.VISIBLE
                    btnGbr.visibility = View.VISIBLE
                    btnGbrB.visibility = View.VISIBLE
                    OstemCrownbox.visibility = View.GONE
                }
            }
        }

        binding.btnIbs.setOnClickListener {

            State_ibs = if (State_ibs == false) true else false
            total_Price += if (State_ibs == true) 90 else -90
            binding.btnIbs.setBackgroundResource(if (State_ibs == true) R.drawable.type2 else R.drawable.type1)
            if (State_ibs == true)
            {
                binding.apply {
                    btnOstem.visibility = View.GONE
                    btnGbr.visibility = View.GONE
                    btnGbrB.visibility = View.GONE

                    IbsCrownBox.visibility = View.VISIBLE
                }
            }
            else
            {
                binding.apply {
                    btnOstem.visibility = View.VISIBLE
                    btnGbr.visibility = View.VISIBLE
                    btnGbrB.visibility = View.VISIBLE

                    IbsCrownBox.visibility = View.GONE
                }
            }

        }


        //뼈이식(Gbr) 복잡
        binding.btnGbr.setOnClickListener {
            State_GbrA = if (State_GbrA == false) true else false
            total_Price += if (State_GbrA == true) 30 else -30
            binding.btnGbr.setBackgroundResource(if (State_GbrA == true) R.drawable.type2 else R.drawable.type1)

        }
        //뼈이식(Gbr) 복잡
        binding.btnGbrB.setOnClickListener {
            State_GbrB = if (State_GbrB == false) true else false
            total_Price += if (State_GbrB == true) 50 else -50
            binding.btnGbrB.setBackgroundResource(if (State_GbrB == true) R.drawable.type2 else R.drawable.type1)

        }

        // 보철

        binding.btnCrown.setOnClickListener {


            State_Crown = if(State_Crown == false ) true else false
            binding.btnCrown.setBackgroundResource(if (State_Crown == true) R.drawable.type2 else R.drawable.type1)
            if(State_Crown == true){

                binding.apply {
                    btnZir.visibility = View.VISIBLE
                    btnPfm.visibility = View.VISIBLE
                    btnMetal.visibility = View.VISIBLE
                    btnGoldCrown.visibility = View.VISIBLE
                    btnImpL.visibility = View.GONE
                }
            }
            else{
                binding.apply {
                    btnZir.visibility = View.GONE
                    btnPfm.visibility = View.GONE
                    btnMetal.visibility = View.GONE
                    btnGoldCrown.visibility = View.GONE
                    btnImpL.visibility = View.VISIBLE
                }
            }



        }
        //Ostem 라인 Zir, Pfm, Metal 눌렀을때 버튼처리
        binding.btnZirOs.setOnClickListener {
            State_ZirOs = if (State_ZirOs == false) true else false
            binding.btnZirOs.setBackgroundResource(if (State_ZirOs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_ZirOs == true) 50 else -50
        }
        binding.btnPfmOs.setOnClickListener {
            State_PfmOs = if (State_PfmOs == false) true else false
            binding.btnPfmOs.setBackgroundResource(if (State_PfmOs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_PfmOs == true) 40 else -40
        }
        binding.btnMetalOs.setOnClickListener {
            State_MetalOs = if (State_MetalOs == false) true else false
            binding.btnMetalOs.setBackgroundResource(if (State_MetalOs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_MetalOs == true) 35 else -35
        }
        //Ibs 라인 Zir, Pfm, Metal
        binding.btnZirIb.setOnClickListener {
            State_ZirIbs = if (State_ZirIbs == false) true else false
            binding.btnZirIb.setBackgroundResource(if (State_ZirIbs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_ZirIbs == true) 50 else -50
        }
        binding.btnPfmIb.setOnClickListener {
            State_PfmIbs = if (State_PfmIbs == false) true else false
            binding.btnPfmIb.setBackgroundResource(if (State_PfmIbs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_PfmIbs == true) 40 else -40
        }
        binding.btnMetalIb.setOnClickListener {
            State_MetalIbs = if (State_MetalIbs == false) true else false
            binding.btnMetalIb.setBackgroundResource(if (State_MetalIbs == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_MetalIbs == true) 35 else -35
        }
        //Crown라인 지르코니아를 눌렀을때 버튼처리

        binding.btnZir.setOnClickListener {
            State_Zir = if (State_Zir == false) true else false
            binding.btnZir.setBackgroundResource(if (State_Zir == true) R.drawable.type2 else R.drawable.type1)
            if(State_Zir == true){
                binding.apply {
                    btnPfm.visibility = View.GONE
                    btnMetal.visibility = View.GONE
                    // A:전치부  B:구치부
                    btnZirA.visibility = View.VISIBLE
                    btnZirB.visibility = View.VISIBLE

                }
            }
            else{
                binding.apply {
                    btnZirA.visibility = View.GONE
                    btnZirB.visibility = View.GONE

                    btnPfm.visibility = View.VISIBLE
                    btnMetal.visibility = View.VISIBLE
                }
            }
        }

        //전치부
        binding.btnZirA.setOnClickListener {
            State_ZirA = if (State_ZirA == false) true else false
            binding.btnZirA.setBackgroundResource(if (State_ZirA == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_ZirA == true) 60 else -60
        }

        //구치부

        binding.btnZirB.setOnClickListener {
            State_ZirB = if (State_ZirB == false) true else false
            binding.btnZirB.setBackgroundResource(if (State_ZirB == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_ZirB == true) 50 else -50
            Log.d("지르","구치부:${State_ZirB}")
        }

        //Pfm
        binding.btnPfm.setOnClickListener {
            State_Pfm = if (State_Pfm == false) true else false
            binding.btnPfm.setBackgroundResource(if (State_Pfm == true) R.drawable.type2 else R.drawable.type1)
            total_Price += if (State_Pfm == true) 40 else -40
        }

        //Metal
        binding.btnMetal.setOnClickListener {
            State_Metal = if (State_Metal == false) true else false
            total_Price += if (State_Metal == true) 35 else -35
            binding.btnMetal.setBackgroundResource(if (State_Metal == true) R.drawable.type2 else R.drawable.type1)
        }
        //Gold Crown
        binding.btnGoldCrown.setOnClickListener {
            State_GoldCrown = if (State_GoldCrown == false) true else false
            total_Price += if (State_GoldCrown == true) 80 else -80
            binding.btnGoldCrown.setBackgroundResource(if (State_GoldCrown == true) R.drawable.type2 else R.drawable.type1)
        }

        binding.btnEndo.setOnClickListener {
            price_Endo = if(price_Endo == 0) 8 else 0
            binding.btnEndo.setBackgroundResource(if (price_Endo == 8) R.drawable.type2 else R.drawable.type1)

            if(price_Endo == 8){
                binding.apply {
                    btnPost.visibility = View.VISIBLE
                    btnCore.visibility = View.VISIBLE
                    btnMe.visibility = View.VISIBLE
                }
            }
            else{
                binding.apply {
                    btnPost.visibility = View.GONE
                    btnCore.visibility = View.GONE
                    btnMe.visibility = View.GONE
                }
            }

        }
        binding.btnPost.setOnClickListener {
            price_Post = if (price_Post == 0) 15 else 0
            total_Price += if (price_Post == 15) 15 else -15

            binding.btnPost.setBackgroundResource(if (price_Post == 15) R.drawable.type2 else R.drawable.type1)


        }

        binding.btnCore.setOnClickListener {
            price_Core = if (price_Core == 0) 10 else 0
            total_Price += if (price_Core == 10) 10 else -10
            binding.btnCore.setBackgroundResource(if (price_Core == 10) R.drawable.type2 else R.drawable.type1)

        }

        binding.btnCavity.setOnClickListener {

            price_Cavatiy = if (price_Cavatiy == 0) 10 else 0
            binding.btnCavity.setBackgroundResource(if (price_Cavatiy == 10) R.drawable.type2 else R.drawable.type1)
            if(price_Cavatiy == 10){

                binding.apply {
                    btnHybridInlay.visibility = View.VISIBLE
                    btnEmaxInlay.visibility = View.VISIBLE
                    btnLesin.visibility = View.VISIBLE
                }
            }
            else{
                binding.apply {
                    btnHybridInlay.visibility = View.GONE
                    btnEmaxInlay.visibility = View.GONE
                    btnLesin.visibility = View.GONE
                }
            }

        }
        binding.btnHybridInlay.setOnClickListener {
            price_HybridInlay = if (price_HybridInlay == 0) 35 else 0
            binding.btnHybridInlay.setBackgroundResource(if (price_HybridInlay == 35) R.drawable.type2 else R.drawable.type1)


        }
        binding.btnEmaxInlay.setOnClickListener {
            price_EmaxInlay = if (price_EmaxInlay == 0) 40 else 0
            binding.btnEmaxInlay.setBackgroundResource(if (price_EmaxInlay == 40) R.drawable.type2 else R.drawable.type1)


        }
        binding.btnLesin.setOnClickListener {
            price_Lesin = if (price_Lesin == 0) 10 else 0
            binding.btnLesin.setBackgroundResource(if (price_Lesin == 10) R.drawable.type2 else R.drawable.type1)
        }

        binding.btnDenture.setOnClickListener {
            price_Denture = if(price_Denture == 0) 10 else 0
            binding.btnDenture.setBackgroundResource(if (price_Denture == 10) R.drawable.type2 else R.drawable.type1)

            if(price_Denture == 10){

                binding.apply {
                    btnDentureA.visibility = View.VISIBLE
                    btnDentureB.visibility = View.VISIBLE
                }
            }
            else{
                binding.apply {
                    btnDentureA.visibility = View.GONE
                    btnDentureB.visibility = View.GONE
                }
            }


        }

        binding.btnDentureA.setOnClickListener {
            price_DentureA = if (price_DentureA == 0) 130 else 0
            binding.btnDentureA.setBackgroundResource(if (price_DentureA == 130) R.drawable.type2 else R.drawable.type1)

        }
        binding.btnDentureB.setOnClickListener {
            price_DentureB = if (price_DentureB == 0) 150 else 0
            binding.btnDentureB.setBackgroundResource(if (price_DentureB == 150) R.drawable.type2 else R.drawable.type1)

        }



        // 치식 클릭에 따른 상태 처리
        binding.teeth18.setOnClickListener {
            state18 = if (state18 == false) true else false
            state18_txt = if (state18 == false) "" else "#18"
            binding.teeth18.setBackgroundResource(if (state18 == true) R.drawable.teeth3 else R.drawable.teeth1)
        }

        binding.teeth17.setOnClickListener {
            state17 = if (state17 == false) true else false
            state17_txt = if (state17 == false) "" else "#17"
            binding.teeth17.setBackgroundResource(if (state17 == true) R.drawable.teeth3 else R.drawable.teeth1)
        }

        binding.teeth16.setOnClickListener {
            state16 = if (state16 == false) true else false
            state16_txt = if (state16 == false) "" else "#16"
            binding.teeth16.setBackgroundResource(if (state16 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth15.setOnClickListener {
            state15 = if (state15 == false) true else false
            state15_txt = if (state15 == false) "" else "#15"
            binding.teeth15.setBackgroundResource(if (state15 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth14.setOnClickListener {
            state14 = if (state14 == false) true else false
            state14_txt = if (state14 == false) "" else "#14"
            binding.teeth14.setBackgroundResource(if (state14 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth13.setOnClickListener {
            state13 = if (state13 == false) true else false
            state13_txt = if (state13 == false) "" else "#13"
            binding.teeth13.setBackgroundResource(if (state13 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth12.setOnClickListener {
            state12 = if (state12 == false) true else false
            state12_txt = if (state12 == false) "" else "#12"
            binding.teeth12.setBackgroundResource(if (state12 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth11.setOnClickListener {
            state11 = if (state11 == false) true else false
            state11_txt = if (state11 == false) "" else "#11"
            binding.teeth11.setBackgroundResource(if (state11 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth21.setOnClickListener {
            state21 = if (state21 == false) true else false
            state21_txt = if (state21 == false) "" else "#21"
            binding.teeth21.setBackgroundResource(if (state21 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth22.setOnClickListener {
            state22 = if (state22 == false) true else false
            state22_txt = if (state22 == false) "" else "#22"
            binding.teeth22.setBackgroundResource(if (state22 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth23.setOnClickListener {
            state23 = if (state23 == false) true else false
            state23_txt = if (state23 == false) "" else "#23"
            binding.teeth23.setBackgroundResource(if (state23 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth24.setOnClickListener {
            state24 = if (state24 == false) true else false
            state24_txt = if (state24 == false) "" else "#24"
            binding.teeth24.setBackgroundResource(if (state24 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth25.setOnClickListener {
            state25 = if (state25 == false) true else false
            state25_txt = if (state25 == false) "" else "#25"
            binding.teeth25.setBackgroundResource(if (state25 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth26.setOnClickListener {
            state26 = if (state26 == false) true else false
            state21_txt = if (state26 == false) "" else "#26"
            binding.teeth26.setBackgroundResource(if (state26 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth27.setOnClickListener {
            state27 = if (state27 == false) true else false
            state27_txt = if (state27 == false) "" else "#27"
            binding.teeth27.setBackgroundResource(if (state27 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth28.setOnClickListener {
            state28 = if (state28 == false) true else false
            state28_txt = if (state28 == false) "" else "#21"
            binding.teeth28.setBackgroundResource(if (state28 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth38.setOnClickListener {
            state38 = if (state38 == false) true else false
            state38_txt = if (state38 == false) "" else "#38"
            binding.teeth38.setBackgroundResource(if (state38 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth37.setOnClickListener {
            state37 = if (state37 == false) true else false
            state37_txt = if (state37 == false) "" else "#37"
            binding.teeth37.setBackgroundResource(if (state37 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth36.setOnClickListener {
            state36 = if (state36 == false) true else false
            state36_txt = if (state36 == false) "" else "#36"
            binding.teeth36.setBackgroundResource(if (state36 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth35.setOnClickListener {
            state35 = if (state35 == false) true else false
            state28_txt = if (state35 == false) "" else "#35"
            binding.teeth35.setBackgroundResource(if (state35 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth34.setOnClickListener {
            state34 = if (state34 == false) true else false
            state34_txt = if (state34 == false) "" else "#34"
            binding.teeth34.setBackgroundResource(if (state34 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth33.setOnClickListener {
            state33 = if (state33 == false) true else false
            state33_txt = if (state33 == false) "" else "#33"
            binding.teeth33.setBackgroundResource(if (state33 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth32.setOnClickListener {
            state32 = if (state32 == false) true else false
            state32_txt = if (state32 == false) "" else "#32"
            binding.teeth32.setBackgroundResource(if (state32 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth31.setOnClickListener {
            state31 = if (state31 == false) true else false
            state31_txt = if (state31 == false) "" else "#31"
            binding.teeth31.setBackgroundResource(if (state31 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth41.setOnClickListener {
            state41 = if (state41 == false) true else false
            state41_txt = if (state41 == false) "" else "#41"
            binding.teeth41.setBackgroundResource(if (state41 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth42.setOnClickListener {
            state42 = if (state42 == false) true else false
            state42_txt = if (state42 == false) "" else "#42"
            binding.teeth42.setBackgroundResource(if (state42 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth43.setOnClickListener {
            state43 = if (state43 == false) true else false
            state43_txt = if (state43 == false) "" else "#43"
            binding.teeth43.setBackgroundResource(if (state43 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth44.setOnClickListener {
            state44 = if (state44 == false) true else false
            state44_txt = if (state44 == false) "" else "#44"
            binding.teeth44.setBackgroundResource(if (state44 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth45.setOnClickListener {
            state45 = if (state45 == false) true else false
            state45_txt = if (state45 == false) "" else "#45"
            binding.teeth45.setBackgroundResource(if (state45 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth46.setOnClickListener {
            state46 = if (state46 == false) true else false
            state46_txt = if (state46 == false) "" else "#46"
            binding.teeth46.setBackgroundResource(if (state46 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth47.setOnClickListener {
            state47 = if (state47 == false) true else false
            state47_txt = if (state47 == false) "" else "#47"
            binding.teeth47.setBackgroundResource(if (state47 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }
        binding.teeth48.setOnClickListener {
            state48 = if (state48 == false) true else false
            state48_txt = if (state48 == false) "" else "#41"
            binding.teeth48.setBackgroundResource(if (state48 == true) R.drawable.teeth2 else R.drawable.teeth1)
        }


        // 저장 버튼을 눌렀을때, 버튼처리
        binding.btnSave.setOnClickListener {
            val count = countTrueTeeth()
            if (count == 0 ){
                AlertDialog.Builder(requireContext())
                    .setTitle("알림")
                    .setMessage("치아를 선택해주세요!")
                    .setPositiveButton("확인") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
            else{
                val currentText = binding.tvSum.text.toString()
                // 새로운 저장 내용 생성
                val newText = "${state18_txt + state17_txt + state16_txt +state15_txt + state14_txt + state13_txt + state12_txt + state11_txt + state21_txt + state22_txt + state23_txt + state24_txt + state25_txt + state26_txt + state27_txt + state28_txt + state31_txt + state32_txt + state33_txt + state34_txt + state35_txt + state36_txt + state37_txt + state38_txt + state48_txt + state47_txt + state46_txt + state45_txt + state44_txt + state43_txt + state42_txt + state41_txt} : ${total_Price * count}만원"

                // 기존 텍스트에 새로운 텍스트를 추가 (줄바꿈 포함)
                val updatedText = if (currentText.isEmpty()) {
                    newText // 기존 텍스트가 없으면 새 텍스트만 표시
                } else {
                    "$currentText\n$newText" // 기존 텍스트가 있으면 새 텍스트를 추가
                }

                // 텍스트 뷰에 업데이트된 텍스트 설정
                binding.tvSum.text = updatedText
                updateInitiallized(binding)
            }

        }
        //초기화 버튼을 눌렀을때, 버튼처리
        binding.btnInitialized.setOnClickListener {
            updateInitiallized(binding)
            updateSum(binding)

        }

    }

    // 총 비용 업데이트 함수
    private fun updateSum(binding: FragmentCrownBinding) {
        if (total_Price == 0) {
            binding.tvSum.text = ""  // total_Price가 0일 경우 tvSum을 비움
        } else {
        }
    }
    private fun updateInitiallized(binding: FragmentCrownBinding){
        resetAllState(binding)
    }


    private fun resetAllState(binding: FragmentCrownBinding) {
        // 총 비용 초기화
        total_Price = 0

        // 각 상태 초기화 10번대
        state18 = false
        state18_txt = ""
        state17 = false
        state17_txt = ""
        state16 = false
        state16_txt = ""
        state15 = false
        state15_txt = ""
        state14 = false
        state14_txt = ""
        state13 = false
        state13_txt = ""
        state12 = false
        state12_txt = ""
        state11 = false
        state11_txt = ""
        //각 상태 초기화 20번대
        state28 = false
        state28_txt = ""
        state27 = false
        state27_txt = ""
        state26 = false
        state26_txt = ""
        state25 = false
        state25_txt = ""
        state24 = false
        state24_txt = ""
        state23 = false
        state23_txt = ""
        state22 = false
        state22_txt = ""
        state21 = false
        state21_txt = ""

        //각 상태 초기화 30번대
        state31 = false
        state31_txt = ""
        state32 = false
        state32_txt = ""
        state33 = false
        state33_txt = ""
        state34 = false
        state34_txt = ""
        state35 = false
        state35_txt = ""
        state36 = false
        state36_txt = ""
        state37 = false
        state37_txt = ""
        state38 = false
        state38_txt = ""

        //각 상태 초기화 40번대
        state41 = false
        state41_txt = ""
        state42 = false
        state42_txt = ""
        state43 = false
        state43_txt = ""
        state44 = false
        state44_txt = ""
        state45 = false
        state45_txt = ""
        state46 = false
        state46_txt = ""
        state47 = false
        state47_txt = ""
        state48 = false
        state48_txt = ""


        // 임플란트 관련 상태 초기화
        State_ImpL = false
        State_ibs = false
        State_ostem = false
        State_GbrA = false
        State_GbrB = false

        // Osstem 보철 관련 상태 초기화
        State_ZirOs = false
        State_PfmOs = false
        State_MetalOs = false

        // Ibs 보철 관련 상태 초기화
        State_ZirIbs = false
        State_PfmIbs = false
        State_MetalIbs = false

        // 보철 관련 상태 초기화
        State_Crown = false
        State_Zir = false
        State_ZirA = false
        State_ZirB = false
        State_Pfm = false
        State_Metal = false
        State_GoldCrown = false


        // 특수 처리 관련 상태 초기화
        price_Endo = 0
        price_Post = 0
        price_Core = 0
        price_Lesin = 0
        price_HybridInlay = 0
        price_EmaxInlay = 0
        price_Denture = 0
        price_DentureA = 0
        price_DentureB = 0

        // 버튼 상태 리셋 (UI 리셋)
        resetButtonStates(binding)


    }

    // 버튼들의 UI 상태 초기화 함수
    private fun resetButtonStates(binding: FragmentCrownBinding) {
        binding.apply {
            // 임플란트 관련 버튼들
            btnImpL.setBackgroundResource(R.drawable.type1)
            btnOstem.setBackgroundResource(R.drawable.type1)
            btnIbs.setBackgroundResource(R.drawable.type1)
            btnGbr.setBackgroundResource(R.drawable.type1)
            btnGbrB.setBackgroundResource(R.drawable.type1)
            btnOstem.visibility = View.GONE
            btnIbs.visibility = View.GONE
            btnGbr.visibility = View.GONE
            btnGbrB.visibility = View.GONE
            OstemCrownbox.visibility = View.GONE
            IbsCrownBox.visibility = View.GONE
            btnImpL.visibility = View.VISIBLE
            btnCrown.visibility = View.VISIBLE
            //osstem 보철 관련 버튼들
            btnZirOs.setBackgroundResource(R.drawable.type1)
            btnPfmOs.setBackgroundResource(R.drawable.type1)
            btnMetalOs.setBackgroundResource(R.drawable.type1)
            //ibs 보철 관련 버튼들
            btnZirIb.setBackgroundResource(R.drawable.type1)
            btnPfmIb.setBackgroundResource(R.drawable.type1)
            btnMetalIb.setBackgroundResource(R.drawable.type1)
            // 보철(Crown) 관련 버튼들
            btnCrown.setBackgroundResource(R.drawable.type1)
            btnZir.setBackgroundResource(R.drawable.type1)
            btnZirA.setBackgroundResource(R.drawable.type1)
            btnZirB.setBackgroundResource(R.drawable.type1)
            btnPfm.setBackgroundResource(R.drawable.type1)
            btnMetal.setBackgroundResource(R.drawable.type1)
            btnGoldCrown.setBackgroundResource(R.drawable.type1)
            btnZir.visibility = View.GONE
            btnZirA.visibility = View.GONE
            btnZirB.visibility = View.GONE
            btnPfm.visibility = View.GONE
            btnMetal.visibility = View.GONE
            btnGoldCrown.visibility = View.GONE

            // 다른 버튼들...
            btnHybridInlay.visibility = View.GONE
            btnEmaxInlay.visibility = View.GONE
            btnLesin.visibility = View.GONE
            btnDentureA.visibility = View.GONE
            btnDentureB.visibility = View.GONE

            // 치아 버튼들
            teeth18.setBackgroundResource(R.drawable.teeth1)
            teeth17.setBackgroundResource(R.drawable.teeth1)
            teeth16.setBackgroundResource(R.drawable.teeth1)
            teeth15.setBackgroundResource(R.drawable.teeth1)
            teeth14.setBackgroundResource(R.drawable.teeth1)
            teeth13.setBackgroundResource(R.drawable.teeth1)
            teeth12.setBackgroundResource(R.drawable.teeth1)
            teeth11.setBackgroundResource(R.drawable.teeth1)
            teeth21.setBackgroundResource(R.drawable.teeth1)
            teeth22.setBackgroundResource(R.drawable.teeth1)
            teeth23.setBackgroundResource(R.drawable.teeth1)
            teeth24.setBackgroundResource(R.drawable.teeth1)
            teeth25.setBackgroundResource(R.drawable.teeth1)
            teeth26.setBackgroundResource(R.drawable.teeth1)
            teeth27.setBackgroundResource(R.drawable.teeth1)
            teeth28.setBackgroundResource(R.drawable.teeth1)

            teeth38.setBackgroundResource(R.drawable.teeth1)
            teeth37.setBackgroundResource(R.drawable.teeth1)
            teeth36.setBackgroundResource(R.drawable.teeth1)
            teeth35.setBackgroundResource(R.drawable.teeth1)
            teeth34.setBackgroundResource(R.drawable.teeth1)
            teeth33.setBackgroundResource(R.drawable.teeth1)
            teeth32.setBackgroundResource(R.drawable.teeth1)
            teeth31.setBackgroundResource(R.drawable.teeth1)
            teeth41.setBackgroundResource(R.drawable.teeth1)
            teeth42.setBackgroundResource(R.drawable.teeth1)
            teeth43.setBackgroundResource(R.drawable.teeth1)
            teeth44.setBackgroundResource(R.drawable.teeth1)
            teeth45.setBackgroundResource(R.drawable.teeth1)
            teeth46.setBackgroundResource(R.drawable.teeth1)
            teeth47.setBackgroundResource(R.drawable.teeth1)
            teeth48.setBackgroundResource(R.drawable.teeth1)
        }

    }

    fun countTrueTeeth(): Int {
        return listOf(state11, state12, state13, state14, state15, state16, state17, state18, state21, state22, state23, state24, state25, state26, state27, state28, state31, state32, state33, state34, state35, state36, state37, state38, state41, state42, state43, state44, state45, state46, state47 ,state48).count { it }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}