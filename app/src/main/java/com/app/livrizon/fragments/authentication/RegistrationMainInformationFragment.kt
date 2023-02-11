package com.app.livrizon.fragments.authentication

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.view.View
import com.app.livrizon.R
import com.app.livrizon.databinding.FragmentRegistrationMainInformationBinding
import com.app.livrizon.fragments.CustomFragment
import com.app.livrizon.function.putString
import com.app.livrizon.function.toDate
import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.edit.profile.save.AccountSave
import com.app.livrizon.request.AuthorizationRequest
import com.app.livrizon.request.HttpListener
import com.app.livrizon.security.Role
import com.app.livrizon.security.token.AccessToken
import com.app.livrizon.security.token.ConfirmToken
import com.app.livrizon.sql.SqlRequest
import com.app.livrizon.values.Parameters
import com.app.livrizon.request.account_pref
import com.app.livrizon.request.token
import kotlinx.coroutines.CoroutineScope

class RegistrationMainInformationFragment : CustomFragment() {
    var save = AccountSave()
    lateinit var binding: FragmentRegistrationMainInformationBinding
    lateinit var datePicker: DatePickerDialog.OnDateSetListener
    lateinit var registrationRequest: HttpListener
    private var calendar = Calendar.getInstance()
    override fun getBindingRoot(): View {
        return binding.root
    }

    override fun initVariable() {
        binding = FragmentRegistrationMainInformationBinding.inflate(layoutInflater)
        save.password = requireArguments().getString(Parameters.password)!!
        datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.tvBirthday.text = calendar.time.time.toDate("dd MMMM yyyy")
        }
    }

    override fun request() {
        registrationRequest = object : HttpListener(requireContext()) {
            override suspend fun body(block: CoroutineScope): Jwt {
                return AuthorizationRequest.registration(save)
            }

            override fun onSuccess(item: Any?) {
                item as Jwt
                val username = (token as ConfirmToken).username
                SqlRequest.saveAccount(Authentication(username, save.password))
                account_pref.putString(Parameters.username, username)
                token = AccessToken(item.jwt)
                navController.navigate(R.id.action_registrationMainInformationFragment_to_registrationTitleFragment)
            }
        }
    }

    override fun initButtons() {
        binding.tvBirthday.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.btnNext.setOnClickListener {
            save.role = Role.user
            save.name = binding.edName.text.toString()
            save.birthday = calendar.time.time.toDate("yyyy-MM-dd")
            registrationRequest.request()
        }
    }

}