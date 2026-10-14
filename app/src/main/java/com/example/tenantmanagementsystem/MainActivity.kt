package com.example.tenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastTenant: Tenant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            var isValid = true
            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                isValid = false
            }
            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Phone number is required"
                isValid = false
            }
            if (rent.isEmpty()) {
                binding.rentEditText.error = "Rent paid is required"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)
            binding.tenant = tenant
            lastTenant = tenant
        }

        binding.callButton.setOnClickListener {
            val tenant = lastTenant
            if (tenant == null) {
                Toast.makeText(this, "Save a tenant first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${tenant.phone}"))
            startActivity(intent)
        }
    }
}
