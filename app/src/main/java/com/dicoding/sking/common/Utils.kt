package com.dicoding.sking.common

import android.net.Uri

object Utils {

    var imgVar: Uri? = null

    val hashMap: HashMap<Int, String> = hashMapOf(
        0 to "Actinic Keratoses",
        1 to "Basal Cell Carcinoma",
        2 to "Benign Keratosis-like lesions",
        3 to "Dermatofibroma",
        4 to "Melanocytic nevi",
        5 to "Vascular lesions",
        6 to "Melanoma"
    )

    val hashMapInfo: HashMap<Int, String> = hashMapOf(
        0 to "Bowen's disease is a very early form of skin cancer that's easily treatable. The main sign is a red, scaly patch on the skin. It affects the squamous cells, which are in the outermost layer of skin, and is sometimes referred to as squamous cell carcinoma in situ.",
        1 to "Basal cell carcinoma is a type of skin cancer that most often develops on areas of skin exposed to the sun, such as the face. On brown and Black skin, basal cell carcinoma often looks like a bump that's brown or glossy black and has a rolled border. Basal cell carcinoma is a type of skin cancer.",
        2 to "Seborrheic keratoses are noncancerous (benign) skin growths that some people develop as they age. They often appear on the back or chest, but can occur on other parts of the body. Seborrheic keratoses grow slowly, in groups or singly. Most people will develop at least one seborrheic keratosis during their lifetime.",
        3 to "Dermatofibroma (superficial benign fibrous histiocytoma) is a common cutaneous nodule of unknown etiology that occurs more often in women. Dermatofibroma frequently develops on the extremities (mostly the lower legs) and is usually asymptomatic, although pruritus and tenderness can be present.",
        4 to "A melanocytic nevus (also known as nevocytic nevus, nevus-cell nevus and commonly as a mole) is a type of melanocytic tumor that contains nevus cells. Some sources equate the term mole with \"melanocytic nevus\", but there are also sources that equate the term mole with any nevus form",
        5 to "Vascular lesions are relatively common abnormalities of the skin and underlying tissues, more commonly known as birthmarks. There are three major categories of vascular lesions: Hemangiomas, Vascular Malformations, and Pyogenic Granulomas.",
        6 to "Melanoma occurs when the pigment-producing cells that give colour to the skin become cancerous. Symptoms might include a new, unusual growth or a change in an existing mole. Melanomas can occur anywhere on the body"
    )
}
