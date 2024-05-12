package com.astecnology.newspluse

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * JetNewsApplication is the custom Application class for the JetNews app.
 * It is annotated with @HiltAndroidApp for Hilt integration.
 */
@HiltAndroidApp
class JetNewsApplication : Application()