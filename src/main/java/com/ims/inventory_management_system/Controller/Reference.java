/*
@Controller
@RequestMapping("/order")
@SessionAttributes("orderForm")
public class OrderController {

    @Autowired
    private CategoryService categoryService; // Service to fetch categories

    @ModelAttribute("orderForm")
    public OrderForm setupForm() {
        return new OrderForm();
    }

    @GetMapping("/start")
    public String showOrderForm(Model model) {
        model.addAttribute("categories", categoryService.getData()); // Fetch categories
        return "orderForm";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("orderForm") OrderForm orderForm,
                             ProductForm productForm) {
        orderForm.getProducts().add(productForm);
        return "redirect:/order/start";
    }

    // ... other methods ...
}
*/
